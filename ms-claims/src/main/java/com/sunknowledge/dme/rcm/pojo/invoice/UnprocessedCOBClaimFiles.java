package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnprocessedCOBClaimFiles {
    private String fileName;
    private String checkOrEFTTraceNumber;
    private String checkIssueOrEFTEffectiveDate;
    private String payerName;
    private Double totalClaimChargeAmount;
    private Double totalClaimPaymentAmount;
    private Double totalPatientResponsibilityAmount;
    private List<UnprocessedCOBClaimsDetail> unprocessedCOBClaimsDetailList;
}
