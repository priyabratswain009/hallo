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
public class SalesOrderInsuranceEntryParameterDTO {
//    private Long salesOrderInsuranceDetailsId;

    private UUID salesOrderInsuranceDetailsUuid;

    //    private Long salesOrderId;
    @NotNull(message = "Sales_Order must be provided")
    UUID salesOrderUUID;

    @NotNull(message = "Patient must be provided")
    @Min(value = 1, message = "Patient must be greater than equals to 1.")
    private Long patientId;

//    @NotNull(message = "Primary_Insurer_Id must be provided")
//    @Min(value = 1, message = "Primary_Insurer_Id must be greater than equals to 1.")
    private Long primaryInsurerId;

//    @NotBlank(message = "Primary_Insurer_Name must be provided")
//    @NotNull(message = "Primary_Insurer_Name must be provided")
    private String primaryInsurerName;

//    @NotBlank(message = "Primary_Insurer_Policy_No must be provided")
//    @NotNull(message = "Primary_Insurer_Policy_No must be provided")
    private String primaryInsurerPolicyNo;

    private String primaryInsurerPatientIdNumber;

//    @NotNull(message = "Primary_Insurer_Effective_Date must be provided")
    private LocalDate primaryInsurerEffectiveDate;

    private String primaryInsurerVerificationStatus;

    private LocalDate primaryInsurerVerificationDate;

//    @NotNull(message = "Primary_Insurer_Pay_Percentage must be provided")
    private Long primaryInsurerPayPercentage;

    private String primaryBox10D;

    private String primaryBox19;

    private String primaryBox24Ia;

    private String primaryBox24Ja;

    private String primaryBox24Jb;

    private String primaryIncludeBox24Jbstatus;

    private String primaryIncludePayerSalesOrderStatus;

    private String primaryWaitForPreviousPayerBeforeBillingStatus;

    private String primaryPayPercentageStatus;

    private Long secondaryInsurerId;

    private String secondaryInsurerName;

    private String secondaryInsurerPolicyNo;

    private String secondaryInsurerPatientIdNumber;

    private LocalDate secondaryInsurerEffectiveDate;

    private String secondaryInsurerVerificationStatus;

    private LocalDate secondaryInsurerVerificationDate;

    private Long secondaryInsurerPayPercentage;

    private String secondaryBox10D;

    private String secondaryBox19;

    private String secondaryBox24Ia;

    private String secondaryBox24Ja;

    private String secondaryBox24Jb;

    private String secondaryIncludeBox24JbStatus;

    private String secondaryIncludePayerSalesOrderStatus;

    private String secondaryWaitPreviousPayerBefrBillingStatus;

    private String secondaryPayPercentageStatus;

    private Long tertiaryInsurerId;

    private String tertiaryInsurerName;

    private String tertiaryInsurerPolicyno;

    private String tertiaryInsurerPatientIdNumber;

    private LocalDate tertiaryInsurerEffectiveDate;

    private String tertiaryInsurerVerificationStatus;

    private LocalDate tertiaryInsurerVerificationDate;

    private Long tertiaryInsurerPayPercentage;

    private String tertiaryBox10D;

    private String tertiaryBox19;

    private String tertiaryBox24Ia;

    private String tertiaryBox24Ja;

    private String tertiaryBox24Jb;

    private String tertiaryIncludeBox24JbStatus;

    private String tertiaryIncludePayerInSalesOrderStatus;

    private String tertiaryWaitPreviousPayerBeforeBillingStatus;

    private String tertiaryPayPercentageStatus;

//    private String insuranceVerificationStatus; //---- Updated during call initiate delivery: before initiating
//    23/08/2023 :: If all exist insurances (primary, secondary and tertiary) verified, then insuranceVerificationStatus="Yes"

    private String coverageVerificationStatus;

    private String excludeFromEligibilityCheckStatus;

    private Long patientPayPercentage;

    private String patientIncludeThisPayorInSalesOrderStatus;

    private String patientWaitForPreviousPayerBeforeBillingStatus;

    private LocalDate workersCompDateOfOnset;

    private String workersCompInjuryRelatedEmploymentStatus;

    private String workersCompInjuryRelatedAutoAccidentStatus;

    private Long workersCompAutoAccidentStateId;

    private String workersCompInjuryRelatedToOtherAccidentStatus;

    private String eclaimsAttachmentStatus;

    private Long attachmentNumber;

    private String typeCode;

    private String transactionCode;

    private String claimsNoteType;

    private String claimsNote;

//    private String status;

//    private String salesOrderNo;

//    private Long primaryInsuranceGroupId;
//
//    private String primaryInsuranceGroupName;
//
//    private Long secondaryInsuranceGroupId;
//
//    private String secondaryInsuranceGroupName;
//
//    private Long tertiaryInsuranceGroupId;
//
//    private String tertiaryInsuranceGroupName;

    private Long primaryInsurancePlanId;

    private String primaryInsurancePlanType;

    private Long secondaryInsurancePlanId;

    private String secondaryInsurancePlanType;

    private Long tertiaryInsurancePlanId;

    private String tertiaryInsurancePlanType;

//    @NotBlank(message = "Patient_First_Name must be provided")
//    @NotNull(message = "Patient_First_Name must be provided")
//    private String primaryInsuranceStateName;
//
//    private String secondaryInsuranceStateName;
//
//    private String tertiaryInsuranceStateName;

    private String primaryInsurancePayerId;

    private String secondaryInsurancePayerId;

    private String tertiaryInsurancePayerId;

    private String primaryInsuranceIndicatorCode;

    private String secondaryInsuranceIndicatorCode;

    private String tertiaryInsuranceIndicatorCode;

//    private Long priceTableId;
//
//    private String priceTableName;

//    @NotBlank(message = "Primary_Insurer_Address_Line_1 must be provided")
//    @NotNull(message = "Primary_Insurer_Address_Line_1 must be provided")
    private String primaryInsurerAddressLine1;

    private String primaryInsurerAddressLine2;

//    @NotBlank(message = "Primary_Insurer_City must be provided")
//    @NotNull(message = "Primary_Insurer_City must be provided")
    private String primaryInsurerCity;

//    @NotBlank(message = "Primary_Insurer_State must be provided")
//    @NotNull(message = "Primary_Insurer_State must be provided")
    private String primaryInsurerState;
//
//    @NotBlank(message = "Primary_Insurer_Zip must be provided")
//    @NotNull(message = "Primary_Insurer_Zip must be provided")
    private String primaryInsurerZip;

//    @NotBlank(message = "Primary_Insurer_Contact_1 must be provided")
//    @NotNull(message = "Primary_Insurer_Contact_1 must be provided")
    private String primaryInsurerContact1;

    private String primaryInsurerFax;

    private String secondaryInsurerAddressLine1;

    private String secondaryInsurerAddressLine2;

    private String secondaryInsurerCity;

    private String secondaryInsurerState;

    private String secondaryInsurerZip;

    private String secondaryInsurerContact1;

    private String secondaryInsurerFax;

    private String tertiaryInsurerAddressLine1;

    private String tertiaryInsurerAddressLine2;

    private String tertiaryInsurerCity;

    private String tertiaryInsurerState;

    private String tertiaryInsurerZip;

    private String tertiaryInsurerContact1;

    private String tertiaryInsurerFax;

    private LocalDate primaryInsurerPolicyEndDate;

//    @NotBlank(message = "Primary_Insurer_Contact_Name must be provided")
//    @NotNull(message = "Primary_Insurer_Contact_Name must be provided")
    private String primaryInsurerContactName;
}
