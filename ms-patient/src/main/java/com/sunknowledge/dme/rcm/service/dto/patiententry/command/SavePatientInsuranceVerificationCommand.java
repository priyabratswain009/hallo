package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientInsuranceVerificationCommand {
    private Long insuranceVerifId;

    private Long patientInsuranceId;

    private String elligibilityStatus;

    private String elligibilityCheckType;

    private String periodYear;

    private String coverageInfo;

    private String networkInfo;

    private Double deductableAmt;

    private Double remainingAmt;

    private String coinsuranceOrCopay;

    private String status;

    private Long createdById;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID patientInsVerifStatUuid;
}
