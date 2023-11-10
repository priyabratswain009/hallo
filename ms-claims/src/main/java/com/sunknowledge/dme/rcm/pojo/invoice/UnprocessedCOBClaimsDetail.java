package com.sunknowledge.dme.rcm.pojo.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnprocessedCOBClaimsDetail {
    private Long claimCob835MasterId;
    private String patientControlNumber;
    private String payerClaimControlNumber;
    private String patientName;
}
