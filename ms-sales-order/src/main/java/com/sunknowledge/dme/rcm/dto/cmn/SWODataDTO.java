package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SWODataDTO {
    private Long sales_order_id;
    private Long facility_npi;
    private String sales_order_no;
    private Long patient_id;
    private String billing_branch_name;
    private String branch_address_line_1;
    private String branch_address_line_2;
    private String city_name;
    private String state_name;
    private String zip_code;
    private String branch_contact_no_1;
    private String branch_contact_no_2;
    private String branch_fax;
    private String patient_first_name;
    private String patient_middle_name;
    private String patient_last_name;
    private String patient_address_line_1;
    private String patient_address_line_2;
    private String branch_city;
    private String branch_state;
    private String branch_zip_code;
    private String patient_contact_no_1;
    private String patient_contact_no_2;
    private LocalDate patient_dob;

    private String ordering_provider_first_name;
    private String ordering_provider_middle_name;
    private String ordering_provider_last_name;
    private String ordering_provider_address_line_1;
    private String ordering_provider_address_line_2;
    private String ordering_provider_city;
    private String ordering_provider_state;
    private String ordering_provider_zip;
    private String ordering_provider_contact_no_1;
    private String ordering_provider_contact_no_2;

    private String ordering_provider_dea;
    private String ordering_provider_phone_1;
    private String ordering_provider_phone_2;
    private String orderingProviderPhone2;
    private String ordering_provider_npi;
    private String ordering_provider_fax;
    private String primary_diagnosis;
    private String icd_10_diagnosis_code_1;
    private String icd_10_diagnosis_code_2;
    private String icd_10_diagnosis_code_3;
    private String icd_10_diagnosis_code_4;
    private String icd_10_diagnosis_code_5;
    private String icd_10_diagnosis_code_6;
    private String icd_10_diagnosis_code_7;
    private String icd_10_diagnosis_code_8;
    private String icd_10_diagnosis_code_9;
    private String icd_10_diagnosis_code_10;
    private String icd_10_diagnosis_code_11;
    private String icd_10_diagnosis_code_12;

    private String primary_insurer_policy_no;


//    private Long salesOrderId;
//    private String salesOrderNo;
//    private String billingBranchName;
//    private String branchAddressLine1;
//    private String branchAddressLine2;
//    private String branchContactNo1;
//    private String branchContactNo2;
//    private String branchFax;
//    private String patientFirstName;
//    private String patientMiddleName;
//    private String patientLastName;
//    private String patientAddressLine1;
//    private String patientAddressLine2;
//    private String patientContactNo1;
//    private String patientContactNo2;
//    private LocalDate patientDob;
//
//    private String orderingProviderDea;
//    private String orderingProviderPhone1;
//    private String orderingProviderPhone2;
//    private String orderingProviderNpi;
//    private String orderingProviderFax;
//    private String primaryDiagnosis;
//    private String icd10DiagnosisCode1;
//    private String icd10DiagnosisCode2;
//    private String icd10DiagnosisCode3;
//    private String icd10DiagnosisCode4;
//    private String icd10DiagnosisCode5;
//    private String icd10DiagnosisCode6;
//    private String icd10DiagnosisCode7;
//    private String icd10DiagnosisCode8;
//    private String icd10DiagnosisCode9;
//    private String icd10DiagnosisCode10;
//    private String icd10DiagnosisCode11;
//    private String icd10DiagnosisCode12;
//
//    private String primaryInsurerPolicyNo;
}
