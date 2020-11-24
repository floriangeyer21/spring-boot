package com.spingboot.demo.controllers;

import com.spingboot.demo.domain.dto.WordResponseDto;
import com.spingboot.demo.service.interfaces.ReviewService;
import com.spingboot.demo.service.interfaces.WordService;
import com.spingboot.demo.service.mappers.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class ReviewController {
    private static final String PAGE_SIZE = "1000";
    private final WordService wordService;
    private final WordMapper mapper;

    @Autowired
    public ReviewController(WordService wordService,
                            WordMapper mapper) {
        this.wordService = wordService;
        this.mapper = mapper;
    }

    @GetMapping("/most-used-words")
    public List<WordResponseDto> getMostUsedWords(
            @RequestParam(defaultValue = PAGE_SIZE) int quantity) {
        return wordService.findMostUsedWords(quantity).stream()
                .map(mapper::mapWordToResponseDto)
                .collect(Collectors.toList());
    }
}