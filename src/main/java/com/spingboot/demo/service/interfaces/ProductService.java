package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.domain.Product;
import com.spingboot.demo.domain.dto.ProductResponseDto;
import java.util.List;

public interface ProductService extends GenericService<Product> {

    List<ProductResponseDto> findMostCommented(int quantity);
}
