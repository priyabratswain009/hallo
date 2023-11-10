package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderSecondaryInsuranceParameterExtendedDTO {
    @NotNull(message = "Sales Order Id not be null")
    private Long salesOrderId;
    //private UUID salesOrderInsuranceDetailsUuid;
    private Long secondaryInsurerId;
    private String secondaryInsurerName;
    private String secondaryInsurerPolicyNo;
    private String secondaryInsurerPatientIdNumber;
    private LocalDate secondaryInsurerEffectiveDate;
    private String secondaryInsurerVerificationStatus;
    private LocalDate secondaryInsurerVerificationDate;
    private Long secondaryInsurerPayPercentage;
    private String secondaryBox10d;
    private String secondaryBox19;
    private String secondaryBox24Ia;
    private String secondaryBox24Ja;
    private String secondaryBox24Jb;
    private String secondaryIncludeBox24JbStatus;
    private String secondaryIncludePayerSalesOrderStatus;
    private String secondaryWaitPreviousPayerBefrBillingStatus;
    private String secondaryPayPercentageStatus;
    private Long secondaryInsuranceGroupId;
    private String secondaryInsuranceGroupName;
    private Long secondaryInsurancePlanId;
    private String secondaryInsurancePlanType;
    private String secondaryInsurancePayerId;
    private String secondaryInsuranceIndicatorCode;
    private String secondaryInsurerAddressLine1;
    private String secondaryInsurerAddressLine2;
    private String secondaryInsurerCity;
    private String secondaryInsurerState;
    private String secondaryInsurerZip;
    private String secondaryInsurerContact1;
    private String secondaryInsurerFax;
    private String secondaryClaimProgram;
}
