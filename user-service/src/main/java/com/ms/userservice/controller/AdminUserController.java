package com.ms.userservice.controller;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.constant.Messages;
import com.ms.userservice.dto.user.UserResponse;
import com.ms.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return new ApiResponse<>(true, userService.getAll(), Messages.SUCCESS.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    @PutMapping("/{id}/role")
    public ApiResponse<Void> changeRole(@PathVariable Long id, @RequestParam String role) {
        userService.changeUserRole(id, role);
        return new ApiResponse<>(true, Messages.UPDATED.name());
    }
}