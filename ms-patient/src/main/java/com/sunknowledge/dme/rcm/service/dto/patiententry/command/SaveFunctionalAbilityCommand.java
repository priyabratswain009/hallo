package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveFunctionalAbilityCommand {
    private Long functionalAbilityId;

    private String functionalAbilityCode;

    private String functionalAbilityName;

    private String functionalAbilityDescription;

    private String status;

    private Long createdById;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID functionalAbilityUuid;
}
