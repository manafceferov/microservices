package com.ms.orderservice.client;

import com.ms.orderservice.config.ApiResponse;
import com.ms.orderservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:2222", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ApiResponse<Object> getProductById(@PathVariable Long id);
}