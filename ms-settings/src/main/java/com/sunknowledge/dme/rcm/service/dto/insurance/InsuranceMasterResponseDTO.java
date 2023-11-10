package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceMasterResponseDTO {
    private String message;
    private Map<String, InsuranceMasterRejectedDTO> skippedData;
}
