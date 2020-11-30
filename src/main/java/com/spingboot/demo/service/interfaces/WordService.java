package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.domain.Word;
import java.util.List;

public interface WordService {

    Word add(Word word);

    List<Word> addAll(List<Word> dtoList);

    Word get(Long id);

    List<Word> getAll();

    List<Word> findMostUsedWords(int quantity);
}
