package com.spingboot.demo.controllers;

import com.spingboot.demo.domain.dto.ProductResponseDto;
import com.spingboot.demo.service.interfaces.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String PAGE_SIZE = "1000";
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/most-commented")
    public List<ProductResponseDto> getMostCommented(
            @RequestParam(defaultValue = PAGE_SIZE) int quantity) {
        return productService.findMostCommented(quantity);
    }
}
