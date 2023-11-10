package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentSearchParameterDTO {
    private UUID patientDocumentUuid;

    private UUID patientMasterUuid;

    private String patientName;

    private LocalDate patientDob;

    private String patientSsn;

    private String email;

    private String documentName;
}
