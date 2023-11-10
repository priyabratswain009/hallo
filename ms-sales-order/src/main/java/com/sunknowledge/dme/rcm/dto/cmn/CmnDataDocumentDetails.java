package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmnDataDocumentDetails {
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

    private Long cmnLoggedBy;

    private LocalDate cmnLoggedDate;

    private Long cmnApprovedBy;

    private LocalDate cmnApprovedDate;

    private Long cmnPrintedBy;

    private LocalDate cmnPrintedDate;

    private String lengthOfNeed;

    private String faxCmnOption;

    private String cmnCoverLetterInclusionOption;

    private Long cmnFaxedBy;

    private LocalDate cmnFaxedDate;

    private String faxStatus;

    private String faxStatusReason;

    private String printCmnOption;

    private String status;

    private UUID cmnIdUuid;

    private String patientPrognosis;

    private String cmnStatus;

    private String refillAuthorised;

    private Long cmnDocumentId;

    private LocalDate generationDate;

    private String initialDocumentName;

    private String outBoundFaxStatus;

    private String outBoundFaxNo;

    private LocalDate outBoundFaxDateTime;

    private String inBoundFaxStatus;

    private String inBoundFaxNo;

    private LocalDate inBoundFaxDateTime;

    private UUID cmnDocumentMasterUuid;

    private String returnDocumentName;

    private String cmnComments;

    private String fileDownloadUri;
}
