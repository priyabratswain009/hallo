package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceMasterDTO {
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

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID insuranceMasterUuid;

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

    private String insuranceIdNo;

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
