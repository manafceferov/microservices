package com.ms.cartservice.client;

import com.ms.cartservice.config.ApiResponse;
import com.ms.cartservice.config.FeignConfig;
import com.ms.cartservice.dto.order.OrderCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", url = "http://localhost:3333", configuration = FeignConfig.class)
public interface OrderClient {

    @PostMapping("/api/orders")
    ApiResponse<Object> createOrder(@RequestBody OrderCreateRequest request);
}