package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoClinicalInsuranceOutputDTO {
    private Long salesOrderId;
    private String salesOrderNo;
    private Long patientId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String patientAddressLine1;
    private String patientAddressLine2;
    private String patientContactNo1;
    private String patientContactNo2;
    private LocalDate patientDob;
    private Long branchId;
    private String billingBranchName;
    private String billingProviderNpi;
    private String billingProviderOrganisationName;
    private String billingProviderAddressLine1;
    private String billingProviderAddressLine2;
    private String billingProviderCity;
    private String billingProviderState;
    private String billingProviderCountry;
    private String billingProviderZipCode;
    private String branchContactPersonName;
    private String branchContactNo1;
    private String orderingProviderFacilityName;
    private String orderingProviderAddressLine1;
    private String orderingProviderAddressLine2;
    private String orderingProviderCity;
    private String orderingProviderState;
    private String orderingProviderZip;
    private String patientFullName;
    private String orderingProviderFullName;
    private String orderingProviderFirstName;
    private String orderingProviderMiddleName;
    private String orderingProviderLastName;
    private String orderingProviderContactNo1;
    private String orderingProviderFax;
    private String orderingProviderNpi;
    private String primaryInsurerPolicyNo;
    private String branchFax;
    private String icd10DiagnosisCode1;
    private String primaryDiagnosis;
    private String patientMemberId;
    private String patientDeliveryState;
    private String branchContactNo2;
}
