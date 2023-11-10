package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsStatus277Data {
    private Long claimStatus277MasterId;
    private String controlNumber;
    private String fileName;
    private String tradingPartnerClaimNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientMemberId;
    private String serviceProviderName;
    private String serviceProviderNpi;
    private Double totalClaimChargeAmount;
    private Double totalClaimPaymentAmount;
    private LocalDate adjudicatedFinalizedDate;
    private LocalDate remittanceDate;
    private String claimStatusCategoryCode;
    private String claimStatusCategoryCodeValue;
    private String statusCode;
    private String statusCodeValue;
    private String clearinghouseTraceNumber;
    private String remittanceTraceNumber;
    private String patientAccountNumber;
    private LocalDate claimServiceBeginDate;
    private LocalDate claimServiceEndDate;
    private LocalDate statusInformationEffectiveDate;
    private Boolean status;
    private Long createdById;
    private String createdByName;
    private LocalDate createdDate;
    private Long updatedById;
    private String updatedByName;
    private LocalDate updateDate;
}
