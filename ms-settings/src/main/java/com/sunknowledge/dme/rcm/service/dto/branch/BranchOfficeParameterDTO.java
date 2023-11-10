package com.sunknowledge.dme.rcm.service.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchOfficeParameterDTO {
    private Long branchId;
    private String branchName;
    private Long branchGroupId;
    private String npi;
    private String ptan;
    private String taxonomyCode;
    private String taxonomyCodeDescription;
    //private String status;
    private String taxIdType;
    private String taxIdNo;
    //private String branchNo;
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
    private String zip;
    private String billingContactNo1;
    private String billingContactNo2;
    private String billingEmail;
    private String branchCompany;
    private String branchGroupName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String signatureName;
    private String itemLocationId;
    private String itemLocationName;
    private Long branchCompanyId;
    private String isDropshipAllowed;
    private String billingTaxonomyCode;
    private String billingNpi;
    private String billingOrganisationName;
    private String billingTaxIdNo;
    private String billingBranchName;
    private String fax;
}
