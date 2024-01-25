package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMasterParameterDTO {
    private UUID roleMasterUuid;
    @NotNull(message="Role_Code must be provided.")
    @NotBlank(message="Role_Code must be provided.")
    private String roleCode;
    @NotNull(message="Role_Name must be provided.")
    @NotBlank(message="Role_Name must be provided.")
    private String roleName;
    private String roleDescription;
    private String status;
}
