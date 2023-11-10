package com.sunknowledge.dme.rcm.service.dto.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureCodeParameterDTO {

    private Long procedureCodeId;

    @NotBlank(message = "Procedure_Code must be provided")
    @NotNull(message = "Procedure_Code must be provided")
    private String procedureCode;

    @NotBlank(message = "Item_Procedure_Code_Desc must be provided")
    @NotNull(message = "Item_Procedure_Code_Desc must be provided")
    private String itemProcedureCodeDesc;

    @NotBlank(message = "Status must be provided")
    @NotNull(message = "Status must be provided")
    private String status;
}
