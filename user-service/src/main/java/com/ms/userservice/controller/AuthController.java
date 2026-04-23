package com.ms.userservice.controller;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.constant.Messages;
import com.ms.userservice.dto.LoginRequest;
import com.ms.userservice.dto.LoginResponse;
import com.ms.userservice.dto.UserCreateRequest;
import com.ms.userservice.dto.UserResponse;
import com.ms.userservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@Valid @RequestBody UserCreateRequest request) {
        return new ApiResponse<>(true, authService.register(request), Messages.CREATED.name());
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return new ApiResponse<>(true, authService.login(request), Messages.SUCCESS.name());
    }
}