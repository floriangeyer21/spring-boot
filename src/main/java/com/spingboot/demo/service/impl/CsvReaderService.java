package com.spingboot.demo.service.impl;

import com.spingboot.demo.exceptions.FileProcessingException;
import com.spingboot.demo.service.interfaces.FileReaderService;

import java.util.List;

public class CsvReaderService implements FileReaderService {

    @Override
    public List<String> read(String path) {
        return null;
    }
}
