package com.sunknowledge.dme.rcm.service.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchOfficeResponseDTO {
    private String message;
    private Map<String, BranchOfficeRejectedDTO> skippedData;
}
