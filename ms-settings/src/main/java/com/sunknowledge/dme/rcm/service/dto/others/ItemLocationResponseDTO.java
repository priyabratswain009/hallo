package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLocationResponseDTO {
    private String message;
    private Map<String, ItemLocationRejectedDTO> skippedData;
}
