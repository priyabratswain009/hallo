package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientDocumentDetailsCommand {
    private Long patientDocumentId;

    private Long patientId;

//    private String patientName;
    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate patientDob;

    private LocalDate patientDod;

    private String patientSsn;

    private String patientGender;

    private Double patientHeight;

    private Double patientWeight;

    private String patientContact1;

    private String patientContact2;

    private String email;

    private Long branchId;

    private String branchName;

    private Long documentTypeId;

    private String documentTypeName;

    private LocalDate documentDate;

    private String documentNote;

    private Long createdById;

    private String status;

    private Long updatedById;

    private String fax;

    private String documentTitle;

    private String documentName;

    private LocalDate scanDate;

    private LocalDate uploadDate;

    private Long uploadById;

    private String uploadByName;

    private Long scanById;

    private String scanByName;

    private String reviewStatus;

    private String reviewReason;

    private LocalDate reviewDate;

    private Long reviewById;

    private String reviewByName;

    private String createdByName;

    private String updatedByName;

    private Long salesOrderId;

    private String salesOrderNo;

    private LocalDate salesOrderCreationDate;

    private UUID patientDocumentUuid;
}
