package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderClinicalEntryParameterDTO {
    UUID salesOrderClinicalDetailsUuid;
    @NotNull(message = "Sales_Order must be provided")
    UUID salesOrderUUID;
    @Min(value = 1, message = "Patient_Id must be greater than equals to 1.")
    @NotNull(message = "Patient_Id must be provided")
    Long patientId;
//    @NotNull(message = "Dea must be provided")
//    @NotBlank(message = "Dea must be provided")
    String orderingProviderDea;
    String renderingProviderDea;
    String referringProviderDea;
    @Min(value = 1, message = "Weight must be greater than equals to 1.")
    @NotNull(message = "Weight must be provided")
    Double patientWeightInLbs;
    @Min(value = 1, message = "Height must be greater than equals to 1.")
    @NotNull(message = "Height must be provided")
    Double heightInInches;
    Long salesRepId;
    String salesRepName;
    Long renderingProviderFacilityNPI;
    Long referringProviderFacilityNPI;
//    @Min(value = 1, message = "Ordering_Provider_Facility_NPI must be greater than equals to 1.")
//    @NotNull(message = "Ordering_Provider_Facility_NPI must be provided")
    Long orderingProviderFacilityNPI;
//    @NotBlank(message = "Icd_10_Diagnosis_Code_1 must be provided")
//    @NotNull(message = "Icd_10_Diagnosis_Code_1 must be provided")
    String icd10DiagnosisCode1;
    String icd10DiagnosisCode2;
    String icd10DiagnosisCode3;
    String icd10DiagnosisCode4;
    String icd10DiagnosisCode5;
    String icd10DiagnosisCode6;
    String icd10DiagnosisCode7;
    String icd10DiagnosisCode8;
    String icd10DiagnosisCode9;
    String icd10DiagnosisCode10;
    String icd10DiagnosisCode11;
    String icd10DiagnosisCode12;
    String epsdtCertificationConditionIndicator;
    String epsdtCertificationCode;
    Long marketingReferralTypeId;
    String marketingReferralTypeDescription;
    Long marketingReferralId;
    String marketingReferralName;
//    @NotNull(message = "Primary_Diagnosis must be provided")
//    @NotBlank(message = "Primary_Diagnosis must be provided")
    String primaryDiagnosis;
    @NotNull(message = "Relationship must be provided")
    @NotBlank(message = "Relationship must be provided")
    String relationship;
    @NotNull(message = "Mode_Of_Contact must be provided")
    @NotBlank(message = "Mode_Of_Contact must be provided")
    String modeOfContact;
    @NotNull(message = "Diagnosis_Code_Type must be provided")
    @NotBlank(message = "Diagnosis_Code_Type must be provided")
    String diagnosisCodeType;
    Long renderingProviderFacilityId;
    String renderingProviderFacilityName;
    Long referringProviderFacilityId;
    String referringProviderFacilityName;
    Long orderingProviderFacilityId;
    String orderingProviderFacilityName;
}
