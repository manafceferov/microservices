package com.ms.userservice.controller;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.constant.Messages;
import com.ms.userservice.dto.user.UserUpdateRequest;
import com.ms.userservice.dto.user.UserResponse;
import com.ms.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, userService.getById(id), Messages.SUCCESS.name());
    }

    @PutMapping
    public ApiResponse<UserResponse> update(@Valid @RequestBody UserUpdateRequest request) {
        return new ApiResponse<>(true, userService.update(request), Messages.UPDATED.name());
    }

    @GetMapping("/{id}/orders")
    public ApiResponse<Object> getUserWithOrders(@PathVariable Long id) {
        return new ApiResponse<>(true, userService.getUserWithOrders(id), Messages.SUCCESS.name());
    }
}