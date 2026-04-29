package com.ms.userservice.client;

import com.ms.userservice.config.ApiResponse;
import com.ms.userservice.config.FeignConfig;
import com.ms.userservice.dto.order.OrderCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service", url = "http://localhost:3333", configuration = FeignConfig.class)
public interface OrderClient {

    @GetMapping("/api/orders/user/{userId}")
    ApiResponse<Object> getOrdersByUserId(@PathVariable Long userId);

    @PostMapping("/api/orders")
    ApiResponse<Object> createOrder(@RequestBody OrderCreateRequest request);
}