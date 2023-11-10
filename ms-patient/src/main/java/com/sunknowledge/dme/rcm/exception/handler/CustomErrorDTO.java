package com.sunknowledge.dme.rcm.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomErrorDTO {
    private final Boolean status;
    private final Object message;
    private final String data;
//    private final String code;
}
