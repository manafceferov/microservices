package com.ms.productservice.service;

import com.ms.productservice.client.UserClient;
import com.ms.productservice.dto.ProductCreateRequest;
import com.ms.productservice.dto.ProductUpdateRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;
import com.ms.productservice.mapper.ProductMapper;
import com.ms.productservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserClient userClient;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          UserClient userClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userClient = userClient;
    }

    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Product already exists");
        }
        Product product = productMapper.toEntity(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse update(ProductUpdateRequest request) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getStock() != null) product.setStock(request.getStock());

        return productMapper.toResponse(productRepository.save(product));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public Object getProductWithUser(Long productId, Long userId) {
        ProductResponse product = getById(productId);
        Object user = userClient.getUserById(userId).getData();

        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("product", product);
        result.put("user", user);
        return result;
    }
}