package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentDetailsByPatientInfoOrDocumentInfo {
    private Long patientDocumentId;

    private Long patientId;

    private String patientName;

    private LocalDate patientDob;

    private String patientSsn;

    private String email;

    private String documentName;
}
