package com.sunknowledge.dme.rcm.service.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchGroupParameterDTO {
    private Long branchGroupId;
    private String branchGroupName;
    private Long companyId;
    //private String status;
}
