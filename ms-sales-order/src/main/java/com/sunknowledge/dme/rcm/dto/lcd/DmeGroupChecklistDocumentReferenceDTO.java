package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmeGroupChecklistDocumentReferenceDTO {
    private Long dme_group_checklist_id;
    private Long dme_group_id;
    private String dme_group_name;
    private Long checklist_id;
    private String checklist_name;
    private Long checklist_document_reference_id;
    private Long document_reference_id;
    private String document_reference_name;
    private String hcpcs_code;

    private Long sales_order_item_details_id;
    private Long sales_order_id;
    private Long patient_id;
    private Long item_location_id;
    private Long sales_order_details_item_id;
    private String sales_order_details_item_name;
    private String sales_order_details_proc_code;
}
