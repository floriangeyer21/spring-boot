package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Review;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.repository.ReviewRepository;
import com.spingboot.demo.service.interfaces.ReviewService;
import com.spingboot.demo.service.mappers.ReviewMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper, ReviewRepository reviewRepository) {
        this.reviewMapper = reviewMapper;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review add(ReviewDto dto) {
        return reviewRepository.save(reviewMapper.mapReviewDtoToReview(dto));
    }

    @Override
    public List<Review> addAll(List<ReviewDto> dtoList) {
        List<Review> reviews = dtoList.stream()
                .map(reviewMapper::mapReviewDtoToReview)
                .collect(Collectors.toList());
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review get(Long id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
