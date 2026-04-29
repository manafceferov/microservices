package com.ms.adminservice.client;

import com.ms.adminservice.config.ApiResponse;
import com.ms.adminservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:1111", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/api/admin/users")
    ApiResponse<Object> getAllUsers();

    @GetMapping("/api/users/{id}")
    ApiResponse<Object> getUserById(@PathVariable Long id);

    @DeleteMapping("/api/admin/users/{id}")
    ApiResponse<Void> deleteUser(@PathVariable Long id);

    @PutMapping("/api/admin/users/{id}/role")
    ApiResponse<Object> changeRole(@PathVariable Long id, @RequestParam String role);
}