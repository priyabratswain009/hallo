package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LcdCriteriaOutcomeDTO {
    private Long slNo;
    private Long soId;
    private Long checklistId;
    private String checklistName;
    private Long coverageCriteriaId;
    private String value;
    private Long itemGroupId;
    private String itemGroupName;
    private String coverageCriteriaName;
    private String coverageCriteriaDetails;



}
