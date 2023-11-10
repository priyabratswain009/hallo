package com.sunknowledge.dme.rcm.service.dto.items;

import com.sunknowledge.dme.rcm.service.dto.itemothersdto.VendorMasterRejectedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterResponseDTO {
    private String message;

    private Map<String, ItemMasterRejectedDTO> skippedData;
}
