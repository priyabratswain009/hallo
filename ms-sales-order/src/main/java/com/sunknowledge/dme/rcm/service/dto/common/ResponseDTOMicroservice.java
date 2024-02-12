package com.sunknowledge.dme.rcm.service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTOMicroservice<T> {
    Boolean outcome;
    String message;
    T data;
    Integer statusCode;
}
