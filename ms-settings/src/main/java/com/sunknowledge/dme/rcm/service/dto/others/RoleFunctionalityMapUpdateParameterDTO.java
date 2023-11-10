package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunctionalityMapUpdateParameterDTO {
    @NotNull(message = "Role_UUID must be provided.")
    UUID roleUUID;
    @NotNull(message = "Functionality_UUID must be provided.")
    UUID functionalityUUID;
    String status;
}
