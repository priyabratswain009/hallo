package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchByPatientIdNoAndBranchIdInputDTO {
    @NotNull(message = "Patient Id Number should not be null")
    private String patientIdNo;

    @NotNull(message = "Branch Id should not be null")
    @Min(value=1, message="Branch_Id must be greater than and equals to 1.")
    private Long branchId;
}
