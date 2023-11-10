package com.sunknowledge.dme.rcm.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileStorageException extends RuntimeException{
    private String message;
}
