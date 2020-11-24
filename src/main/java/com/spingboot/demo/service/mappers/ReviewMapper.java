package com.spingboot.demo.service.mappers;

import com.spingboot.demo.domain.Review;
import com.spingboot.demo.domain.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review mapReviewDtoToReview(ReviewDto dto) {
        return Review.builder()
                .id(dto.getId())
                .denominator(dto.getHelpfulnessDenominator())
                .numerator(dto.getHelpfulnessNumerator())
                .score(dto.getScore())
                .summary(dto.getSummary())
                .text(dto.getText())
                .localDate(dto.getTime())
                .build();
    }
}
