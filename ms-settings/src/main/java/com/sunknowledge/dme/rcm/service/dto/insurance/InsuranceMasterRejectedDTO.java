package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceMasterRejectedDTO {

    private String message;

    private String insuranceName;

    private String insurancePlanType;

    private Long insuranceGroupId;

    private String insuranceIdNo;

    private String status;

    private String insurancePayerIdNo;

}
