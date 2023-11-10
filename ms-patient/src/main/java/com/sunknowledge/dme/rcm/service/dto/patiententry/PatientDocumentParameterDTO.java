package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentParameterDTO {
    @NotNull(message = "Patient_UUID must be provided.")
    private UUID patientUuid;

    @NotNull(message = "Document_Type must be provided.")
    @NotBlank(message = "Document_Type must be provided.")
    private String documentType;

    @NotNull(message = "Status must be provided.")
    @NotBlank(message = "Status must be provided.")
    private String status;

    @NotNull(message = "Description must be provided.")
    @NotBlank(message = "Description must be provided.")
    private String description;

    @NotNull(message = "Patient_Document_Status must be provided.")
    @NotBlank(message = "Patient_Document_Status must be provided.")
    private String patientDocumentStatus;
}
