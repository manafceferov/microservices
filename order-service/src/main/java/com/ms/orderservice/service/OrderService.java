package com.ms.orderservice.service;

import com.ms.orderservice.client.ProductClient;
import com.ms.orderservice.client.UserClient;
import com.ms.orderservice.config.ApiResponse;
import com.ms.orderservice.dto.OrderCreateRequest;
import com.ms.orderservice.dto.OrderUpdateRequest;
import com.ms.orderservice.dto.OrderResponse;
import com.ms.orderservice.entity.Order;
import com.ms.orderservice.mapper.OrderMapper;
import com.ms.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper,
                        UserClient userClient,
                        ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    public OrderResponse create(OrderCreateRequest request) {
        userClient.getUserById(request.getUserId());

        ApiResponse<Object> productResponse = productClient.getProductById(request.getProductId());
        LinkedHashMap<String, Object> product = (LinkedHashMap<String, Object>) productResponse.getData();

        Double price = (Double) product.get("price");
        BigDecimal totalPrice = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = orderMapper.toEntity(request);
        order.setTotalPrice(totalPrice);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return orderMapper.toResponse(order);
    }

    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse changeStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(status);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> getByProductId(Long productId) {
        return orderRepository.findByProductId(productId)
                .stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse update(OrderUpdateRequest request) {
        Order order = orderRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (request.getStatus() != null) order.setStatus(request.getStatus());
        if (request.getQuantity() != null) order.setQuantity(request.getQuantity());

        return orderMapper.toResponse(orderRepository.save(order));
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}