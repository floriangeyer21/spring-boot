package com.spingboot.demo.service;

import com.spingboot.demo.exceptions.FileProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileReadServiceTest {
    private String CORRECT_PATH = "src/main/resources/input.txt";
    private String WRONG_PATH = "wrong_path";
    private final FileReadService fileReadService;

    @Autowired
    public FileReadServiceTest(FileReadService fileReadService) {
        this.fileReadService = fileReadService;
    }

    @Test
    public void correctPath_OK() throws FileProcessingException {
        List<String> expected = new ArrayList<>();
        expected.add("some text for test");
        List<String> actual = fileReadService.read(CORRECT_PATH);
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void incorrectPath_Error() throws FileProcessingException {
        Exception exception = Assertions.assertThrows(FileProcessingException.class, () -> {
            fileReadService.read(WRONG_PATH);
        });
        String expectedMessage = "Can't read file from path wrong_path";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void withNullArgument_EmptyList() throws FileProcessingException {
        List<String > expected = new ArrayList<>();
        List<String> actual = fileReadService.read(null);
        Assertions.assertEquals(expected, actual);
    }
}