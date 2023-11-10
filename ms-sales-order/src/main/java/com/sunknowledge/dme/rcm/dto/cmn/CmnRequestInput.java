package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnRequestInput {
    private Long cmnId;
    private String cmnInitialDate;
    private String cmnLoggedBy;
    private String cmnLoggedDate;
    private String approvedBy;
    private String approvedDate;
    private String cmnRecertificationDate;
    private String cmnExpirationDate;
    private Integer lengthOfNeed;
    private String cmnFaxedBy;
    private String cmnFaxedDate;
    private String faxStatus;
    private String faxStatusReason;
    private String printCmnOption;
    private String patientPrognosis;
    private String refillAuthorized;

    private String outBoundFaxStatus;
    private String outBoundFaxNo;
    private String outBoundFaxDateTime;
    private List<Frequency> frequency;
}
