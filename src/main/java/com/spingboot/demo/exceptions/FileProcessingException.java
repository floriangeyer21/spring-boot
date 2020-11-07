package com.spingboot.demo.exceptions;

import java.io.IOException;

public class FileProcessingException extends IOException {

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}