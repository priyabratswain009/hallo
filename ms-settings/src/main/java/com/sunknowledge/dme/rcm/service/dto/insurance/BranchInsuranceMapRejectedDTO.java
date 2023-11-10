package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchInsuranceMapRejectedDTO {

    private String message;

    private Long branchInsuranceMapId;

    private Long branchId;

    private String branchName;

    private Long insuranceId;

    private String insuranceName;

    private String insuranceIdNo;

    private String insuranceStateName;

    private Long priceTableId;

    private String priceTableName;

    private String branchNpi;

    private String branchPtan;

    private String esubmitterPayorId;

    private String branchTaxonomyCode;

    private String billingProviderOverrideCompanyName;

    private String billingProviderOverrideTaxIdEin;

    private String billingProviderOverrideAddressLine1;

    private String billingProviderOverrideAddressLine2;

    private String billingProviderOverrideCity;

    private String billingProviderOverrideState;

    private String billingProviderOverrideZip;

    private String billingProviderOverrideContact1;

    private String billingProviderOverrideContact2;

    private String billingProviderOverrideFax;

    private String billingProviderOverrideEmail;

    private String payToProviderCompanyName;

    private String payToProviderTaxIdEin;

    private String payToProviderAddressLine1;

    private String payToProviderAddressLine2;

    private String payToProviderCity;

    private String payToProviderState;

    private String payToProviderZip;

    private String payToProviderContact1;

    private String payToProviderContact2;

    private String payToProviderFax;

    private String payToProviderEmail;

    private String submitterName;

    private String submitterContact1;

    private String submitterContact2;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID branchInsuranceMapUuid;
}
