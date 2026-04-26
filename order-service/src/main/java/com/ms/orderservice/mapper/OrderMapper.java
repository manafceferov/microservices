package com.ms.orderservice.mapper;

import com.ms.orderservice.dto.OrderCreateRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderCreateRequest request);

    OrderResponse toResponse(Order order);
}