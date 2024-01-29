package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCMNDetailsForEfaxDTO {
    private Long cmnId;
    private String cmnNumber;
    private String cmnType;
    private String cmnFormName;
    private Long patientId;
    private Long salesOrderId;
    private String salesOrderNo;
    private LocalDate cmnCreateDate;
    private LocalDate cmnInitialDate;
    private LocalDate cmnRevisionDate;
    private LocalDate cmnRecertificationDate;
    private LocalDate cmnExpirationDate;
    private String cmnLoggedBy;
    private LocalDate cmnLoggedDate;
    private String cmnApprovedBy;
    private LocalDate cmnApprovedDate;
    private String cmnPrintedBy;
    private LocalDate cmnPrintedDate;
    private String lengthOfNeed;
    private String faxCmnOption;
    private String cmnCoverLetterInclusionOption;
    private String cmnFaxedBy;
    private LocalDate cmnFaxedDate;
    private String faxStatus;
    private String faxStatusReason;
    private String printCmnOption;
    private Long cmnDocumentId;
    private LocalDate generationDate;
    private String initialDocumentName;
    private String outBoundFaxStatus;
    private String outBoundFaxNo;
    private LocalDate outBoundFaxDateTime;
    private String inBoundFaxStatus;
    private String inBoundFaxNo;
    private LocalDate inBoundFaxDateTime;
    private String cmnDocumentMasterUuid;
    private String cmnIdUuid;
}
