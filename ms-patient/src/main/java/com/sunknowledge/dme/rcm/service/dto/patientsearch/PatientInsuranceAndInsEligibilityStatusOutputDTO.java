package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientInsuranceAndInsEligibilityStatusOutputDTO {
    private Long patientInsuranceId;

    private Long patientId;

    private String payerLevel;

    private String insuranceName;

    private Long insuranceId;

    private String coverageType;

    private String payerContact;

    private String policyNum;

    private String policyGroupNum;

    private LocalDate policyStartDate;

    private LocalDate policyEndDate;

    private Double payPercentage;

    private Double deductableAmt;

    private String relationship;

    private String insuredFirstName;

    private String insuredMiddleName;

    private String insuredSuffix;

    private LocalDate insuredDob;

    private String insuredSsn;

    private String insuredGender;

    private String alwaysCrossoverStatus;

    private String claimCodes;

    private String addtnlClaimInfo;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String insuredLastName;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID patientInsuranceUuid;

    private String memberId;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String insurancePayerIdNo;

    private String expirationStatus;

    private String insurerAddressLine1;

    private String insurerAddressLine2;

    private String insurerCity;

    private String insurerState;

    private String insurerZip;

    private String insurerContact1;

    private String insurerFax;

    private String patientInsuranceEligibilityStatus;
}
