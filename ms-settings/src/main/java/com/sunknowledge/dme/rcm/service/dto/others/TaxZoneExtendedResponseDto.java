package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxZoneExtendedResponseDto {
    private Long id;
    // Status_Name
    private String title;
    private String stateCode;
    private Double taxRate;
}
