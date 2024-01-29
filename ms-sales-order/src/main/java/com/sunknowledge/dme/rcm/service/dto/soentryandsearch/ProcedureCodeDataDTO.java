package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureCodeDataDTO {
    private String procedureCode;
    private String codeDescription;
    private String startDate;
    private String endDate;
    private String numberOfUnits;
}
