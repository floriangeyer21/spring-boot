package com.spingboot.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spingboot.demo.constants.TestConstants;
import com.spingboot.demo.domain.dto.ReviewDto;
import com.spingboot.demo.exceptions.CsvParsingException;
import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvReviewDtoParserServiceTest {
    private final CsvReviewDtoParserService csvReviewDtoParserService;

    @Autowired
    public CsvReviewDtoParserServiceTest(CsvReviewDtoParserService csvReviewDtoParserService) {
        this.csvReviewDtoParserService = csvReviewDtoParserService;
    }

    @Test
    public void correctInput_OK() throws IOException {
        List<CSVRecord> input = CSVFormat.DEFAULT.withHeader().parse(
                new StringReader(TestConstants.CORRECT_MOCK_DATA)).getRecords();
        List<ReviewDto> expected = new ArrayList<>();
        expected.add(ReviewDto.builder()
                .id(1965L)
                .productId("B001FQ0UEE")
                .userId("ABWUZU0SCL5PW")
                .profileName("Howard M.")
                .helpfulnessNumerator(2L)
                .helpfulnessDenominator(2L)
                .score(5L)
                .time(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                        1313539200L), ZoneId.systemDefault()))
                .summary("Absolutely wonderful")
                .text("A family favorite.  These are really "
                        + "unique treats.  The wonderful taste of flowers. "
                        + " The violet and rose are great.  Orange blossom"
                        + " wasn't to my liking.  The company that makes them"
                        + " have been around for hundreds of years.  They are"
                        + " made in a small French town that was featured in a"
                        + " movie called \"Chocolat.\"")
                .build());
        List<ReviewDto> actual = csvReviewDtoParserService.parseToDomain(input);
        assertEquals(expected, actual);
    }

    @Test
    public void wrongInput_Error() throws IOException {
        List<CSVRecord> input = CSVFormat.DEFAULT.withHeader().parse(
                new StringReader(TestConstants.WRONG_MOCK_DATA)).getRecords();
        Exception exception = assertThrows(CsvParsingException.class, () -> {
            csvReviewDtoParserService.parseToDomain(input);
        });
        String expectedMessage = "Can't parse csv records to domain ";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }
}
