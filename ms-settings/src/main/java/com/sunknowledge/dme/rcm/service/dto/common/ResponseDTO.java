package com.sunknowledge.dme.rcm.service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private Boolean outcome;
    private String message;
    private T data;
    private int statusCode;
}
