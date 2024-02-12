package com.sunknowledge.dme.rcm.service.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnPatientData {
    private Long salesOrderId;
    private String salesOrderNo;
    private String patientIdNo;
    private String patientFirstName;
    private String patientLastName;
}
