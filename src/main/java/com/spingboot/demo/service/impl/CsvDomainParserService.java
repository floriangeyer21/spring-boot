package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.TestDomain;
import com.spingboot.demo.service.interfaces.CsvParserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDomainParserService implements CsvParserService<TestDomain> {

    @Override
    public List<TestDomain> parseToDomain(List<String> input) {
        List<TestDomain> result = new ArrayList<>();

        return null;
    }
}
