package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesOrderEntryParameterDTO {
//    private Long salesOrderId;

//    private String salesOrderNo;

    private UUID salesOrderMasterUuid; //-- If Patient_UUID is null then insert otherwise update

    @Min(value = 1, message = "Patient_Id must be greater than equals to 1.")
    @NotNull(message = "Patient_Id must be provided")
    private Long patientId;

    @NotBlank(message = "Patient_First_Name must be provided")
    @NotNull(message = "Patient_First_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_First_Name")
    private String patientFirstName;

//    @NotBlank(message = "Patient_Middle_Name must be provided")
//    @NotNull(message = "Patient_Middle_Name must be provided")
//    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_Middle_Name")
    private String patientMiddleName;

    @NotBlank(message = "Patient_Last_Name must be provided")
    @NotNull(message = "Patient_Last_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_Last_Name")
    private String patientLastName;

    @NotBlank(message = "Patient_Address_Line1 must be provided")
    @NotNull(message = "Patient_Address_Line1 must be provided")
    private String patientAddressLine1;

    private String patientAddressLine2;

    @NotBlank(message = "Patient_Contact_No1 must be provided")
    @NotNull(message = "Patient_Contact_No1 must be provided")
    private String patientContactNo1;

    private String patientContactNo2;

    @Past(message = "Patient_DOB must be a past date")
    @NotNull(message = "Valid Patient_DOB must be provided")
    private LocalDate patientDob;

    @Min(value = 1, message = "Patient_Height must be greater than equals to 1.")
    @NotNull(message = "Patient_Height must be provided")
    private Double patientHeight;

    @Min(value = 1, message = "Patient_Weight must be greater than equals to 1.")
    @NotNull(message = "Patient_Weight must be provided")
    private Double patientWeight;

    @NotBlank(message = "Patient_Ssn must be provided")
    @NotNull(message = "Patient_Ssn must be provided")
    private String patientSsn;

    @NotBlank(message = "Patient_Gender must be provided")
    @NotNull(message = "Patient_Gender must be provided")
    @Pattern(regexp = RegexConstant.GENDER_REGEX, message = "Provide only appropriate Patient_Gender")
    private String patientGender;

    private String hipaaOnFileStatus;

//    @Min(value = 1, message = "Patient_Branch_Id must be greater than equals to 1.")
//    @NotBlank(message = "Patient_Branch_Id must be provided")
//    @NotNull(message = "Patient_Branch_Id must be provided")
//    private Long patientBranchId;

    @NotBlank(message = "Branch_Name must be provided")
    @NotNull(message = "Branch_Name must be provided")
    private String branchName;

    @NotNull(message = "Valid Delivery_Schedule_Date_time must be provided")
    @Future(message = "Delivery_Schedule_Date_time must be a future date")
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

    @Min(value = 1, message = "Branch_Id must be greater than equals to 1.")
    @NotNull(message = "Branch_Id must be provided")
    private Long branchId;

//    private String billingBranchName;

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

//    private Long createdById;

//    private LocalDate createdDate;

    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX, message = "Provide only appropriate City_Name")
    @NotBlank(message = "City_Name must be provided")
    @NotNull(message = "City_Name must be provided")
    private String cityName;

    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX, message = "Provide only appropriate State_Name")
    @NotBlank(message = "State_Name must be provided")
    @NotNull(message = "State_Name must be provided")
    private String stateName;

    @NotBlank(message = "Zip_Code must be provided")
    @NotNull(message = "Zip_Code must be provided")
    private String zipCode;

    private String deliveryCityName;

    private String deliveryStateName;

    private String deliveryZipCode;

    private LocalDate patientDod;

    private String posName;

//    private Long updatedById;

//    private Long confirmationById;
//
//    private String confirmationByName;
//
//    private LocalDate confirmationDate;

//    private String createdByName;
//
//    private String updatedByName;
//
//    private LocalDate updatedDate;

//    private Long soControlNumber;  //-- Should be Sales Order No.

//    @NotBlank(message = "Branch_Contact_Person_Name must be provided")
//    @NotNull(message = "Branch_Contact_Person_Name must be provided")
//    private String branchContactPersonName;
//
//    @NotBlank(message = "Branch_NPI must be provided")
//    @NotNull(message = "Branch_NPI must be provided")
//    private String branchNpi;
//
//    @NotBlank(message = "Branch_EIN must be provided")
//    @NotNull(message = "Branch_EIN must be provided")
//    private String branchEin;
//
//    @NotBlank(message = "Branch_Contact_No1 must be provided")
//    @NotNull(message = "Branch_Contact_No1 must be provided")
//    private String branchContactNo1;
//
//    private String branchContactNo2;

    @NotBlank(message = "Patient_Id_No must be provided")
    @NotNull(message = "Patient_Id_No must be provided")
    private String patientIdNo;

    private String posCode;

    private String eclaimCarrierName;

    private String planParticipationCode;

    private String patientMemberId;

    private String contactPersonName;

    private String providerType;

//    @NotBlank(message = "Branch_Address_Line1 must be provided")
//    @NotNull(message = "Branch_Address_Line1 must be provided")
//    private String branchAddressLine1;
//
//    private String branchAddressLine2;
//
//    @NotBlank(message = "Branch_City must be provided")
//    @NotNull(message = "Branch_City must be provided")
//    private String branchCity;
//
//    @NotBlank(message = "Branch_State must be provided")
//    @NotNull(message = "Branch_State must be provided")
//    private String branchState;
//
//    @NotBlank(message = "Branch_Zip_Code must be provided")
//    @NotNull(message = "Branch_Zip_Code must be provided")
//    private String branchZipCode;

//    private String patientDeliveryAddressLine1;
//
//    private String patientDeliveryAddressLine2;
//
//    private String patientDeliveryCity;
//
//    private String patientDeliveryState;
//
//    private String patientDeliveryCountry;
//
//    private String patientDeliveryZip;
//
//    private String patientBillingAddressLine1;
//
//    private String patientBillingAddressLine2;
//
//    private String patientBillingCity;
//
//    private String patientBillingState;
//
//    private String patientBillingCountry;
//
//    private String patientBillingZip;

    private String patientFax;

//    private String branchFax;

    @NotBlank(message = "Patient_Email must be provided")
    @NotNull(message = "Patient_Email must be provided")
    @Pattern(regexp = RegexConstant.EMAIL_REGEX, message = "Provide appropriate Patient_Email")
    private String patientEmail;

    private String relationship;

    private String modeOfContact;

    @NotBlank(message = "Insurance_Id_No must be provided")
    @NotNull(message = "Insurance_Id_No must be provided")
    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX, message = "Provide only appropriate Insurance_Id_No")
    private String insuranceIdNo;

    @NotBlank(message = "Insured_First_Name must be provided")
    @NotNull(message = "Insured_First_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Insured_First_Name")
    private String insuredFirstName;

    @NotBlank(message = "Insured_First_Name must be provided")
    @NotNull(message = "Insured_First_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Insured_First_Name")
    private String insuredLastName;

    @NotBlank(message = "Insured_Address_Line1 must be provided")
    @NotNull(message = "Insured_Address_Line1 must be provided")
    private String insuredAddressLine1;

    private String insuredAddressLine2;

    @NotBlank(message = "Insured_City must be provided")
    @NotNull(message = "Insured_City must be provided")
    private String insuredCity;

    @NotBlank(message = "Insured_State must be provided")
    @NotNull(message = "Insured_State must be provided")
    private String insuredState;

    @NotBlank(message = "Insured_Zip must be provided")
    @NotNull(message = "Insured_Zip must be provided")
    private String insuredZip;

    @NotBlank(message = "Insured_Contact_No must be provided")
    @NotNull(message = "Insured_Contact_No must be provided")
    private String insuredContactNo;

    @NotBlank(message = "Insured_Gender must be provided")
    @NotNull(message = "Insured_Gender must be provided")
    @Pattern(regexp = RegexConstant.GENDER_REGEX, message = "Provide only appropriate Insured_Gender")
    private String insuredGender;

    @NotBlank(message = "Patient_Relationship_Insured must be provided")
    @NotNull(message = "Patient_Relationship_Insured must be provided")
    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

//    private String billingProviderTaxonomy;   // Should be taken from branch_office.
//
//    private String billingProviderNpi;
//
//    private String billingProviderOrganisationName;
//
//    private String billingProviderAddressLine1;
//
//    private String billingProviderAddressLine2;
//
//    private String billingProviderCity;
//
//    private String billingProviderState;
//
//    private String billingProviderCountry;
//
//    private String billingProviderZipCode;

    @Past(message = "Insured_DOB must be a past date")
    @NotNull(message = "Valid Insured_DOB must be provided")
    private LocalDate insuredDob;

    @NotBlank(message = "Branch_Country must be provided")
    @NotNull(message = "Branch_Country must be provided")
    private String branchCountry;

//    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX, message = "Provide only appropriate Branch_Taxonomy")
//    @NotBlank(message = "Branch_Taxonomy must be provided")
//    @NotNull(message = "Branch_Taxonomy must be provided")
//    private String branchTaxonomy;

//    private String primaryInsurerPriceTableId;
//
//    private String primaryInsurerPriceTableName;
//
//    private String inventoryLocationName;
}
