package com.sunknowledge.dme.rcm.pojo.invoice;

public interface UnprocessedCOBClaimsDetailProjection {
    String getFileName();
    String getCheckOrEftTraceNumber();
    String getCheckIssueOrEftEffectiveDate();
    String getPayerName();
    Double getTotalClaimChargeAmount();
    Double getTotalClaimPaymentAmount();
    Double getTotalPatientResponsibilityAmount();
    Long getClaimCob835MasterId();
    String getPatientControlNumber();
    String getPayerClaimControlNumber();
    String getPatientName();
}
