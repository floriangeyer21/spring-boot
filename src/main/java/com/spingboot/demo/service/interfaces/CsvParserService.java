package com.spingboot.demo.service.interfaces;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

public interface CsvParserService<T> {

    List<T> parseToDomain(List<CSVRecord> input);
}
