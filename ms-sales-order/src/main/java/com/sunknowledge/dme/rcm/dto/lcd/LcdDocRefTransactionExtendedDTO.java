package com.sunknowledge.dme.rcm.dto.lcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LcdDocRefTransactionExtendedDTO {
    private Long soLcdDocRefId;
    private Long soId;
    private Long checklistId;
    private String checklistName;
    private Long docRefId;
    private String docRefName;
    private String value;
    private String status;
    private UUID soLcdDocRefTransactionUuid;
    private Long itemGroupId;
    private String itemGroupName;
    private LocalDate createdDate;
    private Long createdById;
    private String createdByName;
    private LocalDate updatedDate;
    private Long updatedById;
    private String updatedByName;
    private Long coverageCriteriaId;
}
