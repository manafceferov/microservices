package com.ms.orderservice.controller;

import com.ms.orderservice.config.ApiResponse;
import com.ms.orderservice.constant.Messages;
import com.ms.orderservice.dto.OrderCreateRequest;
import com.ms.orderservice.dto.OrderUpdateRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<OrderResponse> create(@Valid @RequestBody OrderCreateRequest request) {
        return new ApiResponse<>(true, orderService.create(request), Messages.CREATED.name());
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, orderService.getById(id), Messages.SUCCESS.name());
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAll() {
        return new ApiResponse<>(true, orderService.getAll(), Messages.SUCCESS.name());
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<OrderResponse>> getByUserId(@PathVariable Long userId) {
        return new ApiResponse<>(true, orderService.getByUserId(userId), Messages.SUCCESS.name());
    }

    @GetMapping("/product/{productId}")
    public ApiResponse<List<OrderResponse>> getByProductId(@PathVariable Long productId) {
        return new ApiResponse<>(true, orderService.getByProductId(productId), Messages.SUCCESS.name());
    }

    @PutMapping
    public ApiResponse<OrderResponse> update(@Valid @RequestBody OrderUpdateRequest request) {
        return new ApiResponse<>(true, orderService.update(request), Messages.UPDATED.name());
    }

    @PutMapping("/{id}/status")
    public ApiResponse<OrderResponse> changeStatus(@PathVariable Long id, @RequestParam String status) {
        return new ApiResponse<>(true, orderService.changeStatus(id, status), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }
}