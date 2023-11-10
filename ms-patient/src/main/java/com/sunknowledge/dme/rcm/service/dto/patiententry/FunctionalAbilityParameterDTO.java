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
public class FunctionalAbilityParameterDTO {
    private UUID functionalAbilityUuid;

    @NotBlank(message = "Functional_Ability_Code must be provided")
    @NotNull(message = "Functional_Ability_Code must be provided")
    private String functionalAbilityCode;

    @NotBlank(message = "Functional_Ability_Name must be provided")
    @NotNull(message = "Functional_Ability_Name must be provided")
    private String functionalAbilityName;

    @NotBlank(message = "Description must be provided")
    @NotNull(message = "Description must be provided")
    private String description;

    @NotBlank(message = "Status must be provided")
    @NotNull(message = "Status must be provided")
    private String status;
}
