package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverageCriteriaFileMapExtendedDTO {
    private Long coverageCriteriaFileMapId;
    private Long checklistId;
    private String checklistName;
    private Long documentReferenceId;
    private String documentReferenceName;
    private String fileName;
    private String status;
    private LocalDate createdDate;
    private Long createdById;
    private String createdByName;
    private LocalDate updatedDate;
    private Long updatedById;
    private String updatedByName;
    private UUID coverageCriteriaFileMapUuid;
    private String fileReference;
    private String coverageCriteriaNotes;
    private Long soId;
    private Long itemGroupId;
    private String itemGroupName;
    private Long coverageCriteriaId;
}
