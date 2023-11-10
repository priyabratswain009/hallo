package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleFunctionalityMapParameterDTO {
    @NotNull(message = "Role_UUID must be provided.")
    UUID roleUUID;
    List<UUID> functionalityUUIDs;
    String description;
}
