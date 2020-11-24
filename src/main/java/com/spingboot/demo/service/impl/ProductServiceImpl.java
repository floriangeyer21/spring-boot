package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Product;
import com.spingboot.demo.domain.dto.ProductResponseDto;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.repository.ProductRepository;
import com.spingboot.demo.service.interfaces.ProductService;
import com.spingboot.demo.service.mappers.ProductMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Product add(ReviewDto dto) {
        return productRepository.save(productMapper.mapReviewDtoToProduct(dto));
    }

    @Override
    public List<Product> addAll(List<ReviewDto> dtoList) {
        List<Product> products = dtoList.stream()
                .map(productMapper::mapReviewDtoToProduct)
                .collect(Collectors.toList());
        return productRepository.saveAll(products);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseDto> findMostCommented(int quantity) {
        return productRepository.findMostCommented(PageRequest.of(0, quantity));
    }
}
