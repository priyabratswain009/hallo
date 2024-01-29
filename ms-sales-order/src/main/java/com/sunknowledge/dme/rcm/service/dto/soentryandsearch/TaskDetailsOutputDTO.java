package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailsOutputDTO {
    private Long taskDetailsId;

    private String taskNo;

    private Long taskId;

    private String taskName;

    private String taskReason;

    private String severity;

    private String subjectText;

    private String descriptionText;

    private Long assignedToId;

    private LocalDate assignmentDate;

    private LocalDate dateNeeded;

    private LocalDate dateCompleted;

    private String taskState;

    private String deactiveStatus;

    private Long objectId;

    private String objectName;

    private String objectInstanceIdNo;

    private String assignToName;

    private UUID objectInstanceUuid;

    private Long wipStatusId;

    private String wipStatusName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID taskDetailsUuid;
}
