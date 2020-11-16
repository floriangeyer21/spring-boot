package com.spingboot.demo.service.interfaces;

import java.util.List;

public interface FileReaderService<T> {

    List<T> read(String path);
}
