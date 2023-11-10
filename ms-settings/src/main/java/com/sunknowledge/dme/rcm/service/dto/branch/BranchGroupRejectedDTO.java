package com.sunknowledge.dme.rcm.service.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchGroupRejectedDTO {

    private String message;
    private Long branchGroupId;
    private String branchGroupName;
    private Long companyId;
}
