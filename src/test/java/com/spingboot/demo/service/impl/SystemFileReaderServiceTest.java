package com.spingboot.demo.service.impl;

import com.spingboot.demo.constants.TestConstants;
import com.spingboot.demo.exceptions.FileProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SystemFileReaderServiceTest {
    private final SystemFileReaderService systemFileReaderService;

    @Autowired
    public SystemFileReaderServiceTest(SystemFileReaderService systemFileReaderService) {
        this.systemFileReaderService = systemFileReaderService;
    }

    @Test
    public void correctPath_OK() {
        List<String> expected = new ArrayList<>();
        expected.add("some text for test");
        List<String> actual = systemFileReaderService.read(TestConstants.CORRECT_PATH);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void incorrectPath_Error() {
        Exception exception = Assertions.assertThrows(FileProcessingException.class, () -> {
            systemFileReaderService.read(TestConstants.WRONG_PATH);
        });
        String expectedMessage = "Can't read file from path wrong_path";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void withNullArgument_EmptyList() {
        List<String> expected = new ArrayList<>();
        List<String> actual = systemFileReaderService.read(null);
        Assertions.assertEquals(expected, actual);
    }
}
