package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsCOB835Data {
    private Long claimCob835MasterId;
    private String patientControlNumber;
    private String payerClaimControlNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientMemberId;
    private String checkOrEFTTraceNumber;
    private LocalDate checkIssueOrEFTEffectiveDate;
    private String creditOrDebitFlagCode;
    private String paymentMethodCode;
    private Double totalClaimChargeAmount;
    private Double totalClaimPaymentAmount;
    private Double totalPatientResponsibilityAmount;
    private Boolean crossoverCarrierName;
    private String entityIdentifierCode;
    private String entityTypeQualifier;
    private String payerName;
    private String payeeName;
    private String payeeNpi;
    private LocalDate claimReceivedDate;
    private String fileName;
    private LocalDate receivedOn;
    private Boolean status;
    private Long createdById;
    private String createdByName;
    private LocalDate createdDate;
    private Long updatedById;
    private String updatedByName;
    private LocalDate updateDate;
}
