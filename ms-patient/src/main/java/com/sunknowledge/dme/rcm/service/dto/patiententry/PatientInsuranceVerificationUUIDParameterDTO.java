package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientInsuranceVerificationUUIDParameterDTO {
    private UUID patientInsVerifStatUuid;

    @NotNull(message = "Patient_Insurance_UUID must be provided")
    private UUID patientInsuranceUuid;

    @NotBlank(message = "Eligibility_Status must be provided")
    @NotNull(message = "Eligibility_Status must be provided")
    private String elligibilityStatus;

    @NotBlank(message = "Eligibility_Check_Type must be provided")
    @NotNull(message = "Eligibility_Check_Type must be provided")
    private String elligibilityCheckType;

    private String periodYear;

    private String coverageInfo;

    private String networkInfo;

    private Double deductableAmt;

    private Double remainingAmt;

    private String coinsuranceOrCopay;
}
