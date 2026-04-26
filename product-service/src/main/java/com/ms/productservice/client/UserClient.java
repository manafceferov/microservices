//package com.ms.productservice.client;
//
//import com.ms.productservice.config.ApiResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "user-service", url = "http://localhost:1111")
//public interface UserClient {
//
//    @GetMapping("/api/users/{id}")
//    ApiResponse<Object> getUserById(@PathVariable Long id);
//
//    @GetMapping("/api/users")
//    ApiResponse<Object> getAllUsers();
//}