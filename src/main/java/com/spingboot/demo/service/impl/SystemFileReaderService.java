package com.spingboot.demo.service.impl;

import com.spingboot.demo.exceptions.FileProcessingException;
import com.spingboot.demo.service.interfaces.FileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

@Service
public class SystemFileReaderService implements FileReaderService {

    @Override
    public List<String> read(String path) {
        if (path == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(FilenameUtils.separatorsToSystem(path)))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            throw new FileProcessingException("Can't read file from path " + path, e);
        }
        return result;
    }
}