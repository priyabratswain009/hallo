package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchInsuranceMapExtendedForUpdateDTO {
    @NotNull(message = "Insurance_Id must be provided.")
    @Min(value=1, message="Insurance_Id must be greater than or equal to 1")
    private Long insuranceId;
    @NotNull(message = "Branch_Id must be provided.")
    @Min(value=1, message="Branch_Id must be greater than or equal to 1")
    private Long branchId;

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
}
