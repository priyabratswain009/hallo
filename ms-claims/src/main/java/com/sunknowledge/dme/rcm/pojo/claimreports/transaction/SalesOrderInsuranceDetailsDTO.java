package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderInsuranceDetailsDTO {
    private Long salesOrderInsuranceDetailsId;

    private Long salesOrderId;

    private Long patientId;

    private Long primaryInsurerId;

    private String primaryInsurerName;

    private String primaryInsurerPolicyNo;

    private String primaryInsurerPatientIdNumber;

    private LocalDate primaryInsurerEffectiveDate;

    private String primaryInsurerVerificationStatus;

    private LocalDate primaryInsurerVerificationDate;

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

    private String insuranceVerificationStatus;

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

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String salesOrderNo;

    private String createdByName;

    private String updatedByName;

    private Long primaryInsuranceGroupId;

    private String primaryInsuranceGroupName;

    private Long secondaryInsuranceGroupId;

    private String secondaryInsuranceGroupName;

    private Long tertiaryInsuranceGroupId;

    private String tertiaryInsuranceGroupName;

    private Long primaryInsurancePlanId;

    private String primaryInsurancePlanType;

    private Long secondaryInsurancePlanId;

    private String secondaryInsurancePlanType;

    private Long tertiaryInsurancePlanId;

    private String tertiaryInsurancePlanType;

    private UUID salesOrderInsuranceDetailsUuid;

    private String primaryInsurancePayerId;

    private String secondaryInsurancePayerId;

    private String tertiaryInsurancePayerId;

    private String primaryInsuranceIndicatorCode;

    private String secondaryInsuranceIndicatorCode;

    private String tertiaryInsuranceIndicatorCode;

    private Long priceTableId;

    private String priceTableName;

    private String primaryInsurerAddressLine1;

    private String primaryInsurerAddressLine2;

    private String primaryInsurerCity;

    private String primaryInsurerState;

    private String primaryInsurerZip;

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

    private String primaryInsurerContactName;

    private String primaryClaimProgram;

    private String secondaryClaimProgram;

    private String tertiaryClaimProgram;
}
