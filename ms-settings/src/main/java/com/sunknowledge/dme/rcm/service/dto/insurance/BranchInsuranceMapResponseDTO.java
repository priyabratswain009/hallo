package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchInsuranceMapResponseDTO {
    private String message;
    private Map<String, BranchInsuranceMapRejectedDTO> skippedData;
}
