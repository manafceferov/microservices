package com.ms.userservice.client;

import com.ms.userservice.config.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:2222")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ApiResponse<Object> getProductById(@PathVariable Long id);

    @GetMapping("/api/products")
    ApiResponse<Object> getAllProducts();
}