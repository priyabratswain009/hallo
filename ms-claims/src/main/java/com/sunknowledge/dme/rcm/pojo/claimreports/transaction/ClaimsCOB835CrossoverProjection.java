package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

public interface ClaimsCOB835CrossoverProjection {
    Long getClaimCob835MasterId();
    String getPatientControlNumber();
    String getPayerClaimControlNumber();
    String getCrossoverCarrierName();
    String getCrossoverCarrierPayerId();
    String getCrossoverCarrierPayerName();
}
