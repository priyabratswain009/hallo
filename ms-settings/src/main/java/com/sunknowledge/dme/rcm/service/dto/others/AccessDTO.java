package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessDTO {
    private Boolean manageGroupMembership;
    private Boolean view;
    private Boolean mapRoles;
    private Boolean impersonate;
    private Boolean manage;
}
