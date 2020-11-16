package com.spingboot.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spingboot.demo.constants.TestConstants;
import com.spingboot.demo.domain.TestDomain;
import com.spingboot.demo.exceptions.CsvParsingException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvDomainParserServiceTest {
    private final CsvDomainParserService csvDomainParserService;

    @Autowired
    public CsvDomainParserServiceTest(CsvDomainParserService csvDomainParserService) {
        this.csvDomainParserService = csvDomainParserService;
    }

    @Test
    public void correctInput_OK() throws IOException {
        List<CSVRecord> input = CSVFormat.DEFAULT.withHeader().parse(
                new StringReader(TestConstants.CORRECT_MOCK_DATA)).getRecords();
        List<TestDomain> expected = new ArrayList<>();
        expected.add(TestDomain.builder()
                .id(1L)
                .name("Alice")
                .age(20)
                .build());
        expected.add(TestDomain.builder()
                .id(2L)
                .name("Bob")
                .age(30)
                .build());
        List<TestDomain> actual = csvDomainParserService.parseToDomain(input);
        assertEquals(expected, actual);
    }

    @Test
    public void wrongInput_Error() throws IOException {
        List<CSVRecord> input = CSVFormat.DEFAULT.withHeader().parse(
                new StringReader(TestConstants.WRONG_MOCK_DATA)).getRecords();
        Exception exception = Assertions.assertThrows(CsvParsingException.class, () -> {
            csvDomainParserService.parseToDomain(input);
        });
        String expectedMessage = "Can't parse csv records to domain ";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }
}
