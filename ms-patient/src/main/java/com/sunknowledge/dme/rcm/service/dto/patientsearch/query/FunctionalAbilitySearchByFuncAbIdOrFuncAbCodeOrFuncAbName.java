package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName {
    private Long functionalAbilityId;

    private String functionalAbilityCode;

    private String functionalAbilityName;
}
