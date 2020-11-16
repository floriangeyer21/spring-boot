package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.exceptions.FileProcessingException;
import java.util.List;

public interface FileReaderService {

    List<String> read(String path);
}
