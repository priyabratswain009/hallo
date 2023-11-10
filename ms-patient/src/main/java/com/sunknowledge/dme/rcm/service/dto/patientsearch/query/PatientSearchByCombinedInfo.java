package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchByCombinedInfo {
    private String patientIdNo;
    private Long branchId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String pdob;
    private String ssnNo;
    private String addressLine1;
    private String addressLine2;
    private String pcity;
    private String patientState;
    private String pzip;
    private String phone;
}
