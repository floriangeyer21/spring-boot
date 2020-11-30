package com.spingboot.demo.repository;

import com.spingboot.demo.domain.Product;
import com.spingboot.demo.domain.dto.ProductResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.spingboot.demo.domain.dto.ProductResponseDto(p.productId) "
            + "FROM Review r "
            + "JOIN r.product p "
            + "GROUP BY p.id "
            + "ORDER BY COUNT(p) DESC, p.id DESC")
    List<ProductResponseDto> findMostCommented(Pageable pageable);
}
