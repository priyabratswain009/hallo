package com.sunknowledge.dme.rcm.service.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsuranceMasterParameterDTO {
    private Long insuranceId;

    private String insuranceName;

    private String insurancePlanType;

    private String taxType;

    private String taxIncludedAllowableStatus;

    private Long insuranceGroupId;

    private Long priceTableId;

    private String claimTypeDmeStatus;

    private String claimTypeMajorMadicalStatus;

    private String claimTypePharmacyStatus;

    private Double payPercentage;

    private String enableSecondaryBillingStatus;

    private String amtPrintOnDeliveryTicketStatus;

    private String medigapStatus;

    private Long medigapNum;

    private String notes;

    //private String status;

    private String insurancePayerIdNo;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String contactNo1;

    private String contactNo2;

    private String fax;

    private String efax;

    private String email;

    private String relationship;

    private String modeOfContact;

    private String claimProgramCode;

    private String claimProgramName;

    //private String insuranceIdNo;

    private String insuranceGroupNo;

    private String priceTableName;

    private String claimProgramId;

    private String claimFormName;

    private String insuranceGroupName;

    private String contactPersonFirstName;

    private String contactPersonMiddleName;

    private String contactPersonLastName;

    private String taxTncludedAllowableStatus;

    private String cmsCrossoverInsuranceIdNo;
}
