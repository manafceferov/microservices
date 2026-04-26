package com.ms.adminservice.client;

import com.ms.adminservice.config.ApiResponse;
import com.ms.adminservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://localhost:2222", configuration = FeignConfig.class)
public interface ProductClient {

    @GetMapping("/api/products")
    ApiResponse<Object> getAllProducts();

    @GetMapping("/api/products/{id}")
    ApiResponse<Object> getProductById(@PathVariable Long id);

    @DeleteMapping("/api/products/{id}")
    ApiResponse<Void> deleteProduct(@PathVariable Long id);
}