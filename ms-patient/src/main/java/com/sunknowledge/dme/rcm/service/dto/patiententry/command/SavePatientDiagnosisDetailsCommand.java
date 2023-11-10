package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientDiagnosisDetailsCommand {
    private Long patientDiagnosisId;

    private Long patientId;

    private String diagnosisCodeType;

    private String diagnosisCode;

    private String diagnosisDescription;

    private LocalDate effectiveDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID patientDiagnosisUuid;
}
