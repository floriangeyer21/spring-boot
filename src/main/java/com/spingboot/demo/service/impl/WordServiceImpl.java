package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Product;
import com.spingboot.demo.domain.RoleName;
import com.spingboot.demo.domain.User;
import com.spingboot.demo.domain.Word;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.repository.ProductRepository;
import com.spingboot.demo.repository.RoleRepository;
import com.spingboot.demo.repository.UserRepository;
import com.spingboot.demo.repository.WordRepository;
import com.spingboot.demo.service.interfaces.WordService;
import com.spingboot.demo.service.mappers.ProductMapper;
import com.spingboot.demo.service.mappers.UserMapper;
import com.spingboot.demo.service.mappers.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
