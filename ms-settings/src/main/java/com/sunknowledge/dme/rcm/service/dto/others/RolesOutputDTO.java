package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesOutputDTO {
    private String id;
    private String name;
    private String description;
    private Boolean composite;
    private Boolean clientRole;
    private String containerId;
}
