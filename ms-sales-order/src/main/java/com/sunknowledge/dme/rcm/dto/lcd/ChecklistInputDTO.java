package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistInputDTO {
//    private Long sales_order_id;
//    private Long item_id;
//    private Long dme_group_id;
//    private String dme_group_name;
//    private String hcpcs_code;
//    private Long checklist_id;
//    private String checklist_name;
//    private Long document_reference_id;
//    private String document_reference_name;
//    private Long coverage_criteria_id;
//    private String coverage_criteria_details;
//    private boolean check;
//    private List<DocumentChecklistDTO> documentChecklist;
//    private List<CoverageCriteriaChecklistDTO> coverageCriteriaChecklist;
//    private boolean check;

    private Long salesOrderId;
    private Long itemId;
    private String itemName;
    private String hcpcsCode;
    private Long checklistId;
    private String checklistName;
    private List<Long> documentReferenceId;
    private List<String> documentReferenceName;
    private List<Boolean> documentCheck;
    private List<Long> coverageCriteriaId;
    private List<String> coverageCriteriaDetails;
    private List<Boolean> coverageCheck;
}
