package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListDocumentReferenceMapExtendedDTO {
    private Long checklistDocumentReferenceId;
    private Long checklistId;
    private String checklistName;
    private Long documentReferenceId;
    private String documentReferenceName;
    private String status;
    private UUID checklistDocumentReferenceMapUuid;
    private Long itemGroupId;
    private String itemGroupName;
}
