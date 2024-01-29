package com.sunknowledge.dme.rcm.service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    Boolean outcome;
    String message;
    T data;
    Long statusCode;

    public ResponseDTO(Boolean outcome, String message, T data) {
        this.outcome = outcome;
        this.message = message;
        this.data = data;
    }
}
