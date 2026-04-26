package com.ms.productservice.client;

import com.ms.productservice.config.ApiResponse;
import com.ms.productservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "http://localhost:3333", configuration = FeignConfig.class)
public interface OrderClient {

    @GetMapping("/api/orders/product/{productId}")
    ApiResponse<Object> getOrdersByProductId(@PathVariable Long productId);
}