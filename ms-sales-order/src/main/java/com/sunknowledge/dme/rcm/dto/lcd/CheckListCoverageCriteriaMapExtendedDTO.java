package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListCoverageCriteriaMapExtendedDTO {
    private Long checklistCoverageCriteriaId;
    private Long checklistId;
    private String checklistName;
    private Long coverageCriteriaId;
    private String coverageCriteriaDetails;
    private String status;
    private UUID checklistCoverageCriteriaMapUuid;
    private String coverageCriteriaName;
    private Long itemGroupId;
    private String itemGroupName;
}
