package com.sunknowledge.dme.rcm.service.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchOfficeRejectedDTO {
    private String message;
    private String branchName;
    private Long branchGroupId;
    private String npi;
    private String ptan;
    private String taxonomyCode;
    private String taxonomyCodeDescription;
    private String status;
    private String taxIdType;
    private String taxIdNo;
    private String branchNo;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingCity;
    private String billingState;
    private String billingZipCode;
    private String submitterContactPersonName;
    private String submitterContactPhoneNo1;
    private String submitterContactPhoneNo2;
    private String billingFax;
    private String contactEmail;
    private String payToProviderZip;
    private String contactNo1;
    private String contactNo2;
    private String billingEmail;
    private String branchCompany;
    private String branchGroupName;
    private String payToProviderAddressLine1;
    private String payToProviderAddressLine2;
    private String payToProviderCity;
    private String payToProviderState;
    private String signatureName;
    private String itemLocationId;
    private String itemLocationName;
    private Long branchCompanyId;
    private String isDropshipAllowed;
}
