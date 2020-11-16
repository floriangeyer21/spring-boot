package com.spingboot.demo.service.impl;

import com.spingboot.demo.exceptions.FileProcessingException;
import com.spingboot.demo.service.interfaces.FileReaderService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

@Service
public class CsvReaderService implements FileReaderService<CSVRecord> {

    @Override
    public List<CSVRecord> read(String path) {
        if (path == null) {
            return Collections.emptyList();
        }
        List<CSVRecord> result = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(FilenameUtils.separatorsToSystem(path)))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(bufferedReader);
            records.forEach(result::add);
        } catch (Exception e) {
            throw new FileProcessingException("Can't read file from path " + path, e);
        }
        return result;
    }
}
