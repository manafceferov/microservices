package com.ms.productservice.mapper;

import com.ms.productservice.dto.ProductCreateRequest;
import com.ms.productservice.dto.ProductUpdateRequest;
import com.ms.productservice.dto.ProductResponse;
import com.ms.productservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductCreateRequest request);

    Product toEntity(ProductUpdateRequest request);

    ProductResponse toResponse(Product product);
}