package com.sunknowledge.dme.rcm.service.dto.itemothersdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorMasterResponseDTO {

    private String message;

    private Map<String, VendorMasterRejectedDTO> skippedData;
}
