package com.sunknowledge.dme.rcm.service.dto.patiententry;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientMasterParameterDTO {
    private UUID patientMasterUUID;

    @NotBlank(message = "Patient_First_Name must be provided")
    @NotNull(message = "Patient_First_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_First_Name")
    private String patientFirstName;
    //    @NotBlank(message = "Patient_Middle_Name must be provided")
//    @NotNull(message = "Patient_Middle_Name must be provided")
//   @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_Middle_Name")
    private String patientMiddleName;
    @NotBlank(message = "Patient_Last_Name must be provided")
    @NotNull(message = "Patient_Last_Name must be provided")
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Provide only appropriate Patient_Last_Name")
    private String patientLastName;
   @Past(message = "Valid Date_Of_Birth must be provided")
//    @NotNull(message = "Valid Date_Of_Birth must be provided")
    private LocalDate dob;
//    @NotBlank(message = "Gender must be provided")
//    @NotNull(message = "Gender must be provided")
    private String gender;
//    @NotBlank(message = "SSN must be provided")
//    @NotNull(message = "SSN must be provided")
    private String ssn;
//    @NotNull(message = "Tax_Zone_Id must be provided")
//    @Min(value = 1, message = "Tax_Zone_Id must be greater than equals to 1.")
    private Long taxZoneId;
    private Double discountPercent;
//    @Min(value = 1, message = "Pos_Id must be greater than equals to 1.")
//    @NotNull(message = "Pos_Id must be provided")
    private Long posId;
    private String priorSystemKey;
    //    private String status;
//    @Min(value = 1, message = "Branch_Id must be greater than equals to 1.")
//    @NotNull(message = "Branch_Id must be provided")
    private Long branchId;
    //    @NotBlank(message = "Patient_Id_Number must be provided")
//    @NotNull(message = "Patient_Id_Number must be provided")
//    private String patientIdNumber;
    @NotBlank(message = "Address_Line_1 must be provided")
    @NotNull(message = "Address_Line_1 must be provided")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "City must be provided")
    @NotNull(message = "City must be provided")
    private String city;
    @NotBlank(message = "State must be provided")
    @NotNull(message = "State must be provided")
    private String state;
    @NotBlank(message = "Country must be provided")
    @NotNull(message = "Country must be provided")
    private String country;
    @NotBlank(message = "Zip must be provided")
    @NotNull(message = "Zip must be provided")
    private String zip;
//    @NotBlank(message = "Contact_No_1 must be provided")
//    @NotNull(message = "Contact_No_1 must be provided")
    private String contactNo1;
    private String contactNo2;
    private String fax;
    private String efax;
//    @NotBlank(message = "Email must be provided")
//    @NotNull(message = "Email must be provided")
    @Pattern(regexp = RegexConstant.EMAIL_REGEX, message = "Provide only appropriate Email")
    private String email;
    //private String relationship;
    private String modeOfContact;
    //private String branchName;
    @NotBlank(message = "Billing_Address_Line_1 must be provided")
    @NotNull(message = "Billing_Address_Line_1 must be provided")
    private String billingAddressLine1;
    private String billingAddressLine2;
    @NotBlank(message = "Billing_City must be provided")
    @NotNull(message = "Billing_City must be provided")
    private String billingAddressCity;
    @NotBlank(message = "Billing_Address_State must be provided")
    @NotNull(message = "Billing_Address_State must be provided")
    private String billingAddressState;
    @NotBlank(message = "Billing_Address_Zip must be provided")
    @NotNull(message = "Billing_Address_Zip must be provided")
    private String billingAddressZip;
    private String caregiverName;
    private String caregiverContact;
    private String caregiverRelatinshipPatient;
//    @NotBlank(message = "Tax_Zone_Name must be provided")
//    @NotNull(message = "Tax_Zone_Name must be provided")
    private String taxZoneName;
//    @NotNull(message = "Tax_Rate must be provided")
    private Double taxRate;
    private LocalDate patientDod;
}
