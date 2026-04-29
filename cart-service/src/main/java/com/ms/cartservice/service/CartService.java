package com.ms.cartservice.service;

import com.ms.cartservice.client.OrderClient;
import com.ms.cartservice.client.ProductClient;
import com.ms.cartservice.config.ApiResponse;
import com.ms.cartservice.dto.cart.CartItemAddRequest;
import com.ms.cartservice.dto.cart.CartItemResponse;
import com.ms.cartservice.dto.cart.CartItemUpdateRequest;
import com.ms.cartservice.dto.order.OrderCreateRequest;
import com.ms.cartservice.entity.CartItem;
import com.ms.cartservice.mapper.CartItemMapper;
import com.ms.cartservice.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final ProductClient productClient;
    private final OrderClient orderClient;

    public CartService(CartItemRepository cartItemRepository,
                       CartItemMapper cartItemMapper,
                       ProductClient productClient,
                       OrderClient orderClient) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.productClient = productClient;
        this.orderClient = orderClient;
    }

    public CartItemResponse addToCart(CartItemAddRequest request) {
        // Məhsul mövcuddurmu yoxla
        productClient.getProductById(request.getProductId());

        // Artıq səbətdə varsa quantity artır
        CartItem existing = cartItemRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + request.getQuantity());
            return toResponse(cartItemRepository.save(existing));
        }

        CartItem cartItem = cartItemMapper.toEntity(request);
        return toResponse(cartItemRepository.save(cartItem));
    }

    public List<CartItemResponse> getCart(Long userId) {
        return cartItemRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CartItemResponse update(CartItemUpdateRequest request) {
        CartItem cartItem = cartItemRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
        cartItem.setQuantity(request.getQuantity());
        return toResponse(cartItemRepository.save(cartItem));
    }

    public void removeItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Cart item not found");
        }
        cartItemRepository.deleteById(id);
    }

    @Transactional
    public void checkout(Long userId) {
        List<CartItem> items = cartItemRepository.findByUserId(userId);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        for (CartItem item : items) {
            OrderCreateRequest orderRequest = new OrderCreateRequest();
            orderRequest.setUserId(userId);
            orderRequest.setProductId(item.getProductId());
            orderRequest.setQuantity(item.getQuantity());
            orderClient.createOrder(orderRequest);
        }

        cartItemRepository.deleteByUserId(userId);
    }

    private CartItemResponse toResponse(CartItem cartItem) {
        CartItemResponse response = cartItemMapper.toResponse(cartItem);
        try {
            ApiResponse<Object> productResponse = productClient.getProductById(cartItem.getProductId());
            LinkedHashMap<String, Object> product = (LinkedHashMap<String, Object>) productResponse.getData();
            response.setProductName((String) product.get("name"));
            Double price = ((Number) product.get("price")).doubleValue();
            response.setProductPrice(price);
            response.setTotalPrice(price * cartItem.getQuantity());
        } catch (Exception e) {
            response.setProductName("Məhsul tapılmadı");
        }
        return response;
    }
}