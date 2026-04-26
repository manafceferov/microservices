package com.ms.productservice.controller;

import com.ms.productservice.config.ApiResponse;
import com.ms.productservice.constant.Messages;
import com.ms.productservice.dto.ProductCreateRequest;
import com.ms.productservice.dto.ProductUpdateRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductCreateRequest request) {
        return new ApiResponse<>(true, productService.create(request), Messages.CREATED.name());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
        return new ApiResponse<>(true, productService.getById(id), Messages.SUCCESS.name());
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        return new ApiResponse<>(true, productService.getAll(), Messages.SUCCESS.name());
    }

    @PutMapping
    public ApiResponse<ProductResponse> update(@Valid @RequestBody ProductUpdateRequest request) {
        return new ApiResponse<>(true, productService.update(request), Messages.UPDATED.name());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ApiResponse<>(true, Messages.DELETED.name());
    }

    @GetMapping("/{id}/orders")
    public ApiResponse<Object> getProductWithOrders(@PathVariable Long id) {
        return new ApiResponse<>(true, productService.getProductWithOrders(id), Messages.SUCCESS.name());
    }
}