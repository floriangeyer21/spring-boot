package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Word;
import com.spingboot.demo.repository.WordRepository;
import com.spingboot.demo.service.interfaces.WordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word add(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public List<Word> addAll(List<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public Word get(Long id) {
        return wordRepository.getOne(id.intValue());
    }

    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> findMostUsedWords(int quantity) {
        return wordRepository.findAllByOrderByAmountDesc(PageRequest.of(0, quantity));
    }
}
