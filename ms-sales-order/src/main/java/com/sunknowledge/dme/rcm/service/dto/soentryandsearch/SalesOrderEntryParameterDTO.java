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
public class SalesOrderEntryParameterDTO {
    private UUID salesOrderMasterUuid; //-- If Patient_UUID is null then insert otherwise update

    @Min(value = 1, message = "Patient_Id must be greater than equals to 1.")
    @NotNull(message = "Patient_Id must be provided")
    private Long patientId;

    @NotBlank(message = "Patient_First_Name must be provided")
    @NotNull(message = "Patient_First_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_First_Name")
    private String patientFirstName;

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

    @NotBlank(message = "Branch_Name must be provided")
    @NotNull(message = "Branch_Name must be provided")
    private String branchName;

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

    private String mutualHoldStatus;

    private String holdStatus;

    private String holdReasonDescription;

    private LocalDate stopDate;

    private String stopReasonDescription;

    @Min(value = 1, message = "Branch_Id must be greater than equals to 1.")
    @NotNull(message = "Branch_Id must be provided")
    private Long branchId;

    private Long inventoryLocationId;

    private Long posId;

    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    private Long discountPercentage;

    private String userField1;

    private String userField2;

    private String userField3;

    private String userField4;

    private String reference;

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

    @NotBlank(message = "Patient_Id_No must be provided")
    @NotNull(message = "Patient_Id_No must be provided")
    private String patientIdNo;

    private String posCode;

    private String patientMemberId;

    private String contactPersonName;

    private String providerType;

    private String patientFax;

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

    @Past(message = "Insured_DOB must be a past date")
    @NotNull(message = "Valid Insured_DOB must be provided")
    private LocalDate insuredDob;

}
