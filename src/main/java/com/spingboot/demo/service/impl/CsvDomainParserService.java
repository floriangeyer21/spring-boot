package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.TestDomain;
import com.spingboot.demo.exceptions.CsvParsingException;
import com.spingboot.demo.service.interfaces.CsvParserService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Service
public class CsvDomainParserService implements CsvParserService<TestDomain> {
    private static final String DOMAIN_ID = "id";
    private static final String DOMAIN_NAME = "name";
    private static final String DOMAIN_AGE = "age";

    @Override
    public List<TestDomain> parseToDomain(List<CSVRecord> input) {
        List<TestDomain> result = new ArrayList<>();
        try {
            for (CSVRecord record : input) {
                Long id = Long.valueOf(record.get(DOMAIN_ID));
                String name = record.get(DOMAIN_NAME);
                int age = Integer.parseInt(record.get(DOMAIN_AGE));
                result.add(TestDomain.builder()
                        .id(id)
                        .name(name)
                        .age(age)
                        .build());
            }
        } catch (Exception
                e) {
            throw new CsvParsingException("Can't parse csv records to domain ", e);
        }
        return result;
    }
}
