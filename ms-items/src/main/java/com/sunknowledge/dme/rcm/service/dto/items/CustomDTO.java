package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDTO<T> {
    public Object data;
}
