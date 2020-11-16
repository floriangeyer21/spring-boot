package com.spingboot.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spingboot.demo.constants.TestConstants;
import com.spingboot.demo.exceptions.FileProcessingException;
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
class CsvReaderServiceTest {
    private final CsvReaderService csvReaderService;

    @Autowired
    public CsvReaderServiceTest(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    @Test
    public void correctPath_OK() throws IOException {
        List<CSVRecord> expected = CSVFormat.DEFAULT.withHeader().parse(
                new StringReader(TestConstants.CORRECT_MOCK_DATA)).getRecords();
        List<CSVRecord> actual = csvReaderService.read(TestConstants.CORRECT_PATH_TO_cSV_FILE);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void incorrectPath_Error() {
        Exception exception = Assertions.assertThrows(FileProcessingException.class, () -> {
            csvReaderService.read(TestConstants.WRONG_PATH);
        });
        String expectedMessage = "Can't read file from path wrong_path";
        String actualMessage = exception.getMessage();
        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void withNullArgument_EmptyList() {
        List<CSVRecord> expected = new ArrayList<>();
        List<CSVRecord> actual = csvReaderService.read(null);
        Assertions.assertEquals(expected, actual);
    }
}
