package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentDetailsInputExtendedDTO {

    @NotNull(message = "Document Type should not be null")
    @NotBlank(message = "Document Type should not be blank")
    private String docType;

    @NotNull(message = "Document Name should not be null")
    @NotBlank(message = "Document Name should not be blank")
    private String documentNames;

    @NotNull(message = "Patient UUID should not be blank")
    private UUID patientUUID;

    private String soNo;

    private String soUUID;

    private Boolean isCloudStorage;

    private String operationType;

}
