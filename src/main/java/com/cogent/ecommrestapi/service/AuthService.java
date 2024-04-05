package com.cogent.ecommrestapi.service;

import com.cogent.ecommrestapi.payload.LoginDto;
import com.cogent.ecommrestapi.payload.LoginResponse;
import com.cogent.ecommrestapi.payload.RegisterDto;
import com.cogent.ecommrestapi.payload.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);
    RegisterResponse register(RegisterDto registerDto);
}
