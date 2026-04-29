package com.ms.cartservice.mapper;

import com.ms.cartservice.dto.cart.CartItemAddRequest;
import com.ms.cartservice.dto.cart.CartItemResponse;
import com.ms.cartservice.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toEntity(CartItemAddRequest request);
    CartItemResponse toResponse(CartItem cartItem);
}