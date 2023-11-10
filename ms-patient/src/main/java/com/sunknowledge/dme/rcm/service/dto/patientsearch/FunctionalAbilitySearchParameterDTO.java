package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionalAbilitySearchParameterDTO {
    private UUID functionalAbilityUUID;

    private String functionalAbilityCode;

    private String functionalAbilityName;
}
