package com.ms.adminservice.client;

import com.ms.adminservice.config.ApiResponse;
import com.ms.adminservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service", url = "http://localhost:3333", configuration = FeignConfig.class)
public interface OrderClient {

    @GetMapping("/api/orders")
    ApiResponse<Object> getAllOrders();

    @PutMapping("/api/orders/{id}/status")
    ApiResponse<Object> changeStatus(@PathVariable Long id, @RequestParam String status);

    @DeleteMapping("/api/orders/{id}")
    ApiResponse<Void> deleteOrder(@PathVariable Long id);
}