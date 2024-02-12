package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WipStatusUpdateInfoDTO {
    private UUID objectInstanceIdUuid;
    private Long objectInstanceId;
    private Long wipStatusId;
    private Long taskId;
    private Long objectId;
    private String objectInstanceIdNo;
    private String state;
    private Long assignedToId;
    private String assignedToName;
    private Long assignedById;
    private String assignedByName;
    private LocalDate assignedDate;
    private String assignedStatus;
    private String assignmentNotes;
    private String assignmentStatusNotes;
    private Long wipSetById;
    private String wipSetByName;
    private String wipNotes;
    private LocalDate dateNeeded;
}
