package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverageCriteriaChecklistDTO {
    private Long dme_group_checklist_id;
    private Long dme_group_id;
    private String dme_group_name;
    private Long checklist_id;
    private String checklist_name;
    private Long checklist_coverage_criteria_id;
    private Long coverage_criteria_id;
    private String coverage_criteria_details;
}
