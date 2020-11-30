package com.spingboot.demo.service.inject;

import com.spingboot.demo.domain.Review;
import com.spingboot.demo.domain.Role;
import com.spingboot.demo.domain.RoleName;
import com.spingboot.demo.domain.Word;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.service.impl.CsvReaderService;
import com.spingboot.demo.service.impl.CsvReviewDtoParserService;
import com.spingboot.demo.service.impl.WordCounter;
import com.spingboot.demo.service.interfaces.ReviewService;
import com.spingboot.demo.service.interfaces.RoleService;
import com.spingboot.demo.service.interfaces.WordService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInjector {
    private static final String FILE_PATH = "src/main/resources/reviews_file.csv";
    private final RoleService roleService;
    private final CsvReaderService readerService;
    private final CsvReviewDtoParserService parser;
    private final WordCounter wordCounter;
    private final WordService wordService;
    private final ReviewService reviewService;

    @Autowired
    public DataInjector(RoleService roleService,
                        CsvReviewDtoParserService parser,
                        CsvReaderService readerService,
                        WordCounter wordCounter,
                        WordService wordService,
                        ReviewService reviewService) {
        this.roleService = roleService;
        this.parser = parser;
        this.readerService = readerService;
        this.wordCounter = wordCounter;
        this.wordService = wordService;
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void inject() {
        injectRoles();
        injectData();
    }

    private void injectRoles() {
        Role user = Role.builder()
                .roleName(RoleName.valueOf("USER"))
                .build();
        Role admin = Role.builder()
                .roleName(RoleName.valueOf("ADMIN"))
                .build();
        roleService.addAll(List.of(user, admin));
    }

    private void injectData() {
        List<CSVRecord> records = readerService.read(FILE_PATH);
        List<ReviewDto> reviewDtos = parser.parseToDomain(records);
        List<Review> reviews = reviewService.addAll(reviewDtos);
        Map<String, Long> wordCount = wordCounter.count(reviews);
        List<Word> words = wordCount.entrySet().stream()
                .map(element -> Word.builder()
                        .amount(element.getValue())
                        .word(element.getKey())
                        .build())
                .collect(Collectors.toList());
        wordService.addAll(words);
    }
}
