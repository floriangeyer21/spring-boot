package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Review;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WordCounter {
    private static final String SEPARATOR = "\\W";

    public Map<String, Long> count(List<Review> reviewList) {
        Map<String, Long> words = new HashMap<>();
        for (Review review : reviewList) {
            if (review.getText().isBlank()) {
                continue;
            }
            String[] text = review.getText().toLowerCase().trim().split(SEPARATOR);
            for (String word : text) {
                words.merge(word, 1L, Long::sum);
            }
        }
        return words;
    }
}
