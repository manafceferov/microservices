package com.ms.orderservice.client;

import com.ms.orderservice.config.ApiResponse;
import com.ms.orderservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:1111", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/api/users/{id}")
    ApiResponse<Object> getUserById(@PathVariable Long id);
}