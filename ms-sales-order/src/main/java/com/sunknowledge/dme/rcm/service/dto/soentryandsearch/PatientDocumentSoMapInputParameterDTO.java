package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentSoMapInputParameterDTO {
    @NotNull(message = "Patient So UUID should not be null")
    @NotBlank(message = "Patient So UUID should not be blank")
    private String soUUID;

    @NotNull(message = "Patient So No should not be null")
    @NotBlank(message = "Patient So No should not be blank")
    private String soNo;

    @NotNull(message = "Parameter Type should not be null")
    @NotBlank(message = "Patient Type should not be blank")
    private String parameterType;

    @NotNull(message = "Parameter Value should not be null")
    @NotBlank(message = "Patient Value should not be blank")
    private String parameterValue;

}
