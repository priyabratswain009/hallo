package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxZoneParameterDTO {
    private Long taxZoneId;

    //private Long stateCodeId;

    private String stateName;

    private String stateCode;

    private Double taxRate;

    //private String status;
}
