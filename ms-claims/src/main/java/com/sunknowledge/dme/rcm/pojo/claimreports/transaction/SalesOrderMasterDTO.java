package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderMasterDTO {
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

    private Double patientHeight;

    private Double patientWeight;

    private String patientSsn;

    private String patientGender;

    private String hipaaOnFileStatus;

    private Long patientBranchId;

    private String branchName;

    private LocalDate deliveryScheduleDatetime;

    private LocalDate deliveryActualDatetime;

    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    private String deliveryPhoneNo1;

    private String deliveryPhoneNo2;

    private Long facilityId;

    private String facilityName;

    private String facilityNpi;

    private Long taxZoneId;

    private Double taxRate;

    private String salesOrderNote;

    private String deliveryNote;

    private String deliveryTechnician;

    private String signatureRequiredStatus;

    private String podStatus;

    private LocalDate podStatusDatetime;

    private String podLastMessage;

    private LocalDate podMessageDatetime;

    private String mutualHoldStatus;

    private String holdStatus;

    private String holdReasonDescription;

    private LocalDate stopDate;

    private String stopReasonDescription;

    private Long branchId;

    private String billingBranchName;

    private Long inventoryLocationId;

    private String orderStatus;

    private Long orderClassificationId;

    private Long posId;

    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    private Long discountPercentage;

    private String poNumber;

    private String userField1;

    private String userField2;

    private String userField3;

    private String userField4;

    private String reference;

    private String wipStatus;

    private Long wipDaysInState;

    private Long wipAssignedToId;

    private LocalDate wipDateNeeded;

    private String completedStatus;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String cityName;

    private String stateName;

    private String zipCode;

    private String deliveryCityName;

    private String deliveryStateName;

    private String deliveryZipCode;

    private LocalDate patientDod;

    private String posName;

    private Long updatedById;

    private Long confirmationById;

    private String confirmationByName;

    private LocalDate confirmationDate;

    private String createdByName;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID salesOrderMasterUuid;

    private String branchContactPersonName;

    private String branchNpi;

    private String branchEin;

    private String branchContactNo1;

    private String branchContactNo2;

    private String patientIdNo;

    private String posCode;

    private String eclaimCarrierName;

    private String planParticipationCode;

    private String patientMemberId;

    private String contactPersonName;

    private String providerType;

    private String branchAddressLine1;

    private String branchAddressLine2;

    private String branchCity;

    private String branchState;

    private String branchZipCode;

    private String patientDeliveryAddressLine1;

    private String patientDeliveryAddressLine2;

    private String patientDeliveryCity;

    private String patientDeliveryState;

    private String patientDeliveryCountry;

    private String patientDeliveryZip;

    private String patientBillingAddressLine1;

    private String patientBillingAddressLine2;

    private String patientBillingCity;

    private String patientBillingState;

    private String patientBillingCountry;

    private String patientBillingZip;

    private String patientFax;

    private String branchFax;

    private String patientEmail;

    private String relationship;

    private String modeOfContact;

    private String insuredFirstName;

    private String insuredLastName;

    private String insuredAddressLine1;

    private String insuredAddressLine2;

    private String insuredCity;

    private String insuredState;

    private String insuredZip;

    private String insuredContactNo;

    private String insuredGender;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String billingProviderTaxonomy;

    private String billingProviderNpi;

    private String billingProviderOrganisationName;

    private String billingProviderAddressLine1;

    private String billingProviderAddressLine2;

    private String billingProviderCity;

    private String billingProviderState;

    private String billingProviderCountry;

    private String billingProviderZipCode;

    private LocalDate insuredDob;

    private String branchCountry;

    private String branchTaxonomy;

    private Long primaryInsurerPriceTableId;

    private String primaryInsurerPriceTableName;

    private String inventoryLocationName;

    private String soControlNumber;
}
