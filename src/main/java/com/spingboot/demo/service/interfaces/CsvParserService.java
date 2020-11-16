package com.spingboot.demo.service.interfaces;

import org.apache.catalina.LifecycleState;

import java.util.List;

public interface CsvParserService<T> {

    List<T> parseToDomain(List<String> input);
}
