package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.domain.dto.ReviewDto;
import java.util.List;

public interface GenericService<T> {

    T add(ReviewDto dto);

    List<T> addAll(List<ReviewDto> dtoList);

    T get(Long id);

    List<T> getAll();
}
