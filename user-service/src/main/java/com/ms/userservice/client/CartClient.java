package com.ms.userservice.client;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.config.FeignConfig;
import com.ms.userservice.dto.cart.CartItemAddRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cart-service", url = "http://localhost:5555", configuration = FeignConfig.class)
public interface CartClient {

    @PostMapping("/api/cart")
    ApiResponse<Object> addToCart(@RequestBody CartItemAddRequest request);

    @GetMapping("/api/cart/{userId}")
    ApiResponse<Object> getCart(@PathVariable Long userId);

    @DeleteMapping("/api/cart/{id}")
    ApiResponse<Void> removeItem(@PathVariable Long id);

    @PostMapping("/api/cart/checkout/{userId}")
    ApiResponse<Void> checkout(@PathVariable Long userId);
}