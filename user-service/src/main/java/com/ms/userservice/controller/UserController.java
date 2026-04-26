package com.ms.userservice.controller;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.constant.Messages;
import com.ms.userservice.dto.UserCreateRequest;
import com.ms.userservice.dto.UserUpdateRequest;
import com.ms.userservice.dto.UserResponse;
import com.ms.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        return new ApiResponse<>(true, userService.create(request), Messages.CREATED.name());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, userService.getById(id), Messages.SUCCESS.name());
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return new ApiResponse<>(true, userService.getAll(), Messages.SUCCESS.name());
    }

    @PutMapping
    public ApiResponse<UserResponse> update(@Valid @RequestBody UserUpdateRequest request) {
        return new ApiResponse<>(true, userService.update(request), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    @GetMapping("/{id}/orders")
    public ApiResponse<Object> getUserWithOrders(@PathVariable Long id) {
        return new ApiResponse<>(true, userService.getUserWithOrders(id), Messages.SUCCESS.name());
    }
}