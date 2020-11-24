package com.spingboot.demo.repository;

import com.spingboot.demo.domain.Word;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    
    List<Word> findAllByOrderByAmountDesc(Pageable pageable);
}
