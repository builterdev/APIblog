package com.builterdev.apiblog.service;

import com.builterdev.apiblog.payload.LoginDto;
import com.builterdev.apiblog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}