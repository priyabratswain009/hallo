package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientInsuranceParameterMicroserviceDTO {
    private UUID patientInsuranceUuid;
    @NotNull(message = "Patient_ID must be provided")
    private Long patientID;
    @NotBlank(message = "Payer_Level must be provided")
    @NotNull(message = "Payer_Level must be provided")
    private String payerLevel;
    @NotBlank(message = "Insurance_Name must be provided")
    @NotNull(message = "Insurance_Name must be provided")
    private String insuranceName;
    @NotNull(message = "Insurance_Id must be provided")
    @Min(value=1, message = "Insurance_Id must be greater than equals to 1")
    private Long insuranceId;
    private String coverageType;
    //    @NotBlank(message = "Payer_Contact must be provided")
//    @NotNull(message = "Payer_Contact must be provided")
    private String payerContact;
    //    @NotBlank(message = "Policy_Num must be provided")
//    @NotNull(message = "Policy_Num must be provided") //"^[a-zA-Z0-9]+$"
    //@Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX, message = "Provide only alphanumeric characters for Policy_Num")
    private String policyNum;
    private String policyGroupNum;
    //    @NotNull(message = "Policy_Start_Date must be provided")
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    @NotNull(message = "Pay_Percentage must be provided")
    @Min(value = 1, message = "Pay_Percentage must be greater than equals to 1.")
    private Double payPercentage;
    private Double deductableAmt;
    //    @NotBlank(message = "Relationship must be provided")
//    @NotNull(message = "Relationship must be provided")
//    @Pattern(regexp = RegexConstant.ALPHABET_REGEX, message = "Provide only appropriate Relationship")
    private String relationship;
    //    @NotBlank(message = "Insured_First_Name must be provided")
//    @NotNull(message = "Insured_First_Name must be provided")
    //@Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Insured_First_Name")
    private String insuredFirstName;
    private String insuredMiddleName;
    //    @NotBlank(message = "Insured_Suffix must be provided")
//    @NotNull(message = "Insured_Suffix must be provided")
    private String insuredSuffix;
    //    @NotNull(message = "Appropriate Insured_DOB must be provided")
//    @Past(message = "Appropriate Insured_DOB must be provided")
    private LocalDate insuredDob;
    private String insuredSsn;
    //    @NotBlank(message = "Insured_Gender must be provided")
//    @NotNull(message = "Insured_Gender must be provided")
    //@Pattern(regexp = RegexConstant.ALPHABET_REGEX, message = "Provide only appropriate Insured_Gender")
    private String insuredGender;
    private String alwaysCrossoverStatus;
    private String claimCodes;
    private String addtnlClaimInfo;
    //    @NotBlank(message = "Status must be provided")
//    @NotNull(message = "Status must be provided")
//    private String status;
//    @NotBlank(message = "Insured_Last_Name must be provided")
//    @NotNull(message = "Insured_Last_Name must be provided")
    //@Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Insured_Last_Name")
    private String insuredLastName;
    private String memberId;
    //    private String patientRelationshipInsured;
    private String patientConditionEmployment;
    private String patientConditionAutoAccident;
    private String patientConditionOtherAccident;
    private String insurancePayerIdNo;
    //    @NotBlank(message = "Insurer_Address_Line1 must be provided")
//    @NotNull(message = "Insurer_Address_Line1 must be provided")
    private String insurerAddressLine1;
    private String insurerAddressLine2;
    //    @NotBlank(message = "Insurer_City must be provided")
//    @NotNull(message = "Insurer_City must be provided")
    private String insurerCity;
    //    @NotBlank(message = "Insurer_State must be provided")
//    @NotNull(message = "Insurer_State must be provided")
    private String insurerState;
    //    @NotBlank(message = "Insurer_Zip must be provided")
//    @NotNull(message = "Insurer_Zip must be provided")
    private String insurerZip;
    //    @NotBlank(message = "Insurer_Contact must be provided")
//    @NotNull(message = "Insurer_Contact must be provided")
    private String insurerContact1;
    private String insurerFax;
    private String expirationStatus;
}
