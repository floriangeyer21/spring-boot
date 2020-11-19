package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.exceptions.CsvParsingException;
import com.spingboot.demo.service.interfaces.CsvParserService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class CsvReviewDtoParserService implements CsvParserService<ReviewDto> {
    private static final String ID = "Id";
    private static final String PRODUCT_ID = "ProductId";
    private static final String USER_ID = "UserId";
    private static final String PROFILE_NAME = "ProfileName";
    private static final String HELPFULNESS_NUMERATOR = "HelpfulnessNumerator";
    private static final String HELPFULNESS_DENOMINATOR = "HelpfulnessDenominator";
    private static final String SCORE = "Score";
    private static final String TIME = "Time";
    private static final String SUMMARY = "Summary";
    private static final String TEXT = "Text";

    @Override
    public List<ReviewDto> parseToDomain(List<CSVRecord> input) {
        List<ReviewDto> result = new ArrayList<>();
        try {
            for (CSVRecord record : input) {
                ReviewDto reviewDto = getReviewDtoFromCsvRecord(record);
                result.add(reviewDto);
            }
        } catch (Exception e) {
            throw new CsvParsingException("Can't parse csv records to domain ", e);
        }
        return result;
    }

    private ReviewDto getReviewDtoFromCsvRecord(CSVRecord record) {
        Long id = Long.valueOf(record.get(ID));
        String productId = (record.get(PRODUCT_ID));
        String userId = record.get(USER_ID);
        String profileName = record.get(PROFILE_NAME);
        Long helpfulnessNumerator = Long.valueOf(record.get(HELPFULNESS_NUMERATOR));
        Long helpfulnessDenominator = Long.valueOf(record.get(HELPFULNESS_DENOMINATOR));
        Long score = Long.valueOf(record.get(SCORE));
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                Long.parseLong(record.get(TIME))), ZoneId.systemDefault());
        String summary = record.get(SUMMARY);
        String text = record.get(TEXT);
        return ReviewDto.builder()
                .id(id)
                .userId(userId)
                .productId(productId)
                .profileName(profileName)
                .helpfulnessNumerator(helpfulnessNumerator)
                .helpfulnessDenominator(helpfulnessDenominator)
                .score(score)
                .time(time)
                .summary(summary)
                .text(text)
                .build();
    }
}
