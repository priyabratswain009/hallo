package com.sunknowledge.dme.rcm.application.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceRequest<T> {
    private T data;
}
