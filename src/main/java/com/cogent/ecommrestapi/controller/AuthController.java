package com.cogent.ecommrestapi.controller;


import com.cogent.ecommrestapi.payload.LoginDto;
import com.cogent.ecommrestapi.payload.LoginResponse;
import com.cogent.ecommrestapi.payload.RegisterDto;
import com.cogent.ecommrestapi.payload.RegisterResponse;
import com.cogent.ecommrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = { "/login", "/signin"})
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        LoginResponse response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = { "/register", "/signup"} )
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        RegisterResponse response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
