package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderPatientDocumentSearchInputDTO {
    @NotNull(message = "Parameter Value should not be null")
    private String parameterValue;

    @NotNull(message = "Parameter data Type No should not be null")
    private String operationType;
}
