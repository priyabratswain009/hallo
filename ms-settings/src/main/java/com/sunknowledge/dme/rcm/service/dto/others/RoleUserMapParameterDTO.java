package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserMapParameterDTO {
    @NotNull(message = "Role_UUID must be provided.")
    private UUID roleUUID;
    @NotNull(message = "User UUID must be provided.")
    private UUID userUUID;
    private String description;
}
