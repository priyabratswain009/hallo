package com.sunknowledge.dme.rcm.dto.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDocumentFromSODTO {
    private String documentName;
    private String documentType;
    private Long patientId;
    private String description;
    private String patientDocumentStatus;
}
