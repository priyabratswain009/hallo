package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentReferenceFileMapExtendedDTO {
    private Long documentReferenceFileMapId;
    private Long checklistId;
    private String checklistName;
    private Long coverageCriteriaId;
    private String fileName;
    private String status;
    private LocalDate createdDate;
    private Long createdById;
    private String createdByName;
    private LocalDate updatedDate;
    private Long updatedById;
    private String updatedByName;
    private UUID documentReferenceFileMapUuid;
    private String fileReference;
    private String documentReferenceNotes;
    private Long soId;
    private Long itemGroupId;
    private String itemGroupName;
}
