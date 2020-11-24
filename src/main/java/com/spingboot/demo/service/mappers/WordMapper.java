package com.spingboot.demo.service.mappers;


import com.spingboot.demo.domain.Word;
import com.spingboot.demo.domain.dto.WordResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {
    public WordResponseDto mapWordToResponseDto(Word word) {
        return WordResponseDto.builder()
                .word(word.getWord())
                .amount(word.getAmount())
                .build();
    }
}
