package com.spingboot.demo.service;

import com.spingboot.demo.exceptions.FileProcessingException;
import com.spingboot.demo.service.impl.SystemFileReaderService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemFileReaderServiceTest {
    private static final String CORRECT_PATH = "src/test/resources/input.txt";
    private static final String WRONG_PATH = "wrong_path";
    private final SystemFileReaderService systemFileReaderService;

    @Autowired
    public SystemFileReaderServiceTest(SystemFileReaderService systemFileReaderService) {
        this.systemFileReaderService = systemFileReaderService;
    }

    @Test
    public void correctPath_OK() throws FileProcessingException {
        List<String> expected = new ArrayList<>();
        expected.add("some text for test");
        List<String> actual = systemFileReaderService.read(CORRECT_PATH);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void incorrectPath_Error() throws FileProcessingException {
        Exception exception = Assertions.assertThrows(FileProcessingException.class, () -> {
            systemFileReaderService.read(WRONG_PATH);
        });
        String expectedMessage = "Can't read file from path wrong_path";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void withNullArgument_EmptyList() throws FileProcessingException {
        List<String > expected = new ArrayList<>();
        List<String> actual = systemFileReaderService.read(null);
        Assertions.assertEquals(expected, actual);
    }
}
