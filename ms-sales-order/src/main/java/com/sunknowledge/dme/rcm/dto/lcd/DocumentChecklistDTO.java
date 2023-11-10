package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentChecklistDTO {
    private Long dme_group_checklist_id;
    private Long dme_group_id;
    private String dme_group_name;
    private Long checklist_id;
    private String checklist_name;
    private Long checklist_document_reference_id;
    private Long document_reference_id;
    private String document_reference_name;
    private String hcpcs_code;
}
