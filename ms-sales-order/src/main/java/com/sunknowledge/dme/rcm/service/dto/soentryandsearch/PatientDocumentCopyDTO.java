package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDocumentCopyDTO {
    private Long patientDocumentId;

    private String patientDocumentNo;

    private Long patientId;

    private UUID patientUuid;

    private String patientIdNo;

    private String patientDocumentType;

    private String patientDocumentRepositoryName;

    private String patientDocumentName;

    private String patientDocumentDescription;

    private String patientDocumentStatus;

    private Long uploadedById;

    private String uploadedByName;

    private LocalDate uploadedDate;

    private String status;

    private UUID patientDocumentUuid;
}
