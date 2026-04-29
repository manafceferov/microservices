package com.ms.cartservice.controller;

import com.ms.cartservice.config.ApiResponse;
import com.ms.cartservice.constant.Messages;
import com.ms.cartservice.dto.cart.CartItemAddRequest;
import com.ms.cartservice.dto.cart.CartItemResponse;
import com.ms.cartservice.dto.cart.CartItemUpdateRequest;
import com.ms.cartservice.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ApiResponse<CartItemResponse> addToCart(@Valid @RequestBody CartItemAddRequest request) {
        return new ApiResponse<>(true, cartService.addToCart(request), Messages.CREATED.name());
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<CartItemResponse>> getCart(@PathVariable Long userId) {
        return new ApiResponse<>(true, cartService.getCart(userId), Messages.SUCCESS.name());
    }

    @PutMapping
    public ApiResponse<CartItemResponse> update(@Valid @RequestBody CartItemUpdateRequest request) {
        return new ApiResponse<>(true, cartService.update(request), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    @PostMapping("/checkout/{userId}")
    public ApiResponse<Void> checkout(@PathVariable Long userId) {
        cartService.checkout(userId);
        return new ApiResponse<>(true, Messages.SUCCESS.name());
    }
}