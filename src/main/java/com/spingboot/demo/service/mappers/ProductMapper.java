package com.spingboot.demo.service.mappers;

import com.spingboot.demo.domain.Product;
import com.spingboot.demo.domain.dto.ProductResponseDto;
import com.spingboot.demo.domain.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapReviewDtoToProduct(ReviewDto dto) {
        return Product.builder()
                .productId(dto.getProductId())
                .build();
    }

    public ProductResponseDto mapProductToResponseDto(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getProductId())
                .build();
    }
}
