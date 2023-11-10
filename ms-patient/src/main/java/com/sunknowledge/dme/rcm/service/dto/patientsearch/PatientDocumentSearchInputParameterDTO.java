package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentSearchInputParameterDTO {
    @NotNull(message = "Parameter Value should not be null")
    private String parameterValue;

    @NotNull(message = "Parameter data Type No should not be null")
    private String operationType;
}
