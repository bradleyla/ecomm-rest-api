package com.cogent.ecommrestapi.service.impl;

import com.cogent.ecommrestapi.entity.Role;
import com.cogent.ecommrestapi.entity.User;
import com.cogent.ecommrestapi.exception.ECommerceApiException;
import com.cogent.ecommrestapi.payload.LoginDto;
import com.cogent.ecommrestapi.payload.LoginResponse;
import com.cogent.ecommrestapi.payload.RegisterDto;
import com.cogent.ecommrestapi.payload.RegisterResponse;
import com.cogent.ecommrestapi.repository.RoleRepository;
import com.cogent.ecommrestapi.repository.UserRepository;
import com.cogent.ecommrestapi.security.JwtTokenProvider;
import com.cogent.ecommrestapi.service.AuthService;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("Login successful!");
        response.setError(false);
        return response;
    }

    @Override
    public RegisterResponse register(RegisterDto registerDto) {

        // add check for username existing in database
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("Username already exists in database");
        }
        // add check for email existing in database
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists in database");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // assign roles to the user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        // save user into database
        User savedUser = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setError(false);
        response.setMessage("User has been successfully registered");
        response.setUser(savedUser);
        return response;
    }
}
