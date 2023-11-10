package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDiagnosisParameterDTO {
    private UUID patientDiagnosisUuid;

    @NotNull(message = "Patient_UUID must be provided.")
    private UUID patientUUID;

    @NotNull(message = "Diagnosis_Code_Type must be provided.")
    @NotBlank(message = "Diagnosis_Code_Type must be provided.")
    private String diagnosisCodeType;

    @NotNull(message = "Diagnosis_Code must be provided.")
    @NotBlank(message = "Diagnosis_Code must be provided.")
    private String diagnosisCode;

    @NotNull(message = "Diagnosis_Description must be provided.")
    @NotBlank(message = "Diagnosis_Description must be provided.")
    private String diagnosisDescription;

    @NotNull(message = "Effective_Date must be provided.")
    private LocalDate effectiveDate;
//
//    @NotNull(message = "Status must be provided.")
//    @NotBlank(message = "Status must be provided.")
//    private String status;
}
