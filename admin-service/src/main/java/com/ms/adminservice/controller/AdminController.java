package com.ms.adminservice.controller;

import com.ms.adminservice.config.ApiResponse;
import com.ms.adminservice.constant.Messages;
import com.ms.adminservice.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // USER
    @GetMapping("/users")
    public ApiResponse<Object> getAllUsers() {
        return new ApiResponse<>(true, adminService.getAllUsers(), Messages.SUCCESS.name());
    }

    @GetMapping("/users/{id}")
    public ApiResponse<Object> getUserById(@PathVariable Long id) {
        return new ApiResponse<>(true, adminService.getUserById(id), Messages.SUCCESS.name());
    }

    @PutMapping("/users/{id}/role")
    public ApiResponse<Object> changeUserRole(@PathVariable Long id, @RequestParam String role) {
        return new ApiResponse<>(true, adminService.changeUserRole(id, role), Messages.UPDATED.name());
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    // PRODUCT
    @GetMapping("/products")
    public ApiResponse<Object> getAllProducts() {
        return new ApiResponse<>(true, adminService.getAllProducts(), Messages.SUCCESS.name());
    }

    @GetMapping("/products/{id}")
    public ApiResponse<Object> getProductById(@PathVariable Long id) {
        return new ApiResponse<>(true, adminService.getProductById(id), Messages.SUCCESS.name());
    }

    @DeleteMapping("/products/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    // ORDER
    @GetMapping("/orders")
    public ApiResponse<Object> getAllOrders() {
        return new ApiResponse<>(true, adminService.getAllOrders(), Messages.SUCCESS.name());
    }

    @PutMapping("/orders/{id}/status")
    public ApiResponse<Object> changeOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return new ApiResponse<>(true, adminService.changeOrderStatus(id, status), Messages.UPDATED.name());
    }

    @DeleteMapping("/orders/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long id) {
        adminService.deleteOrder(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }
}