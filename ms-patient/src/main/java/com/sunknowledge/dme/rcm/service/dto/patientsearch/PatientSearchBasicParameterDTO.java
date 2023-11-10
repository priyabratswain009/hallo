package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchBasicParameterDTO {
    private UUID patientMasterUuid;
    private String name;
    private LocalDate dob;
    private String ssnNo;
    private String address;
    private String phone;
    private Long branchId;
    private String branchName;
}
