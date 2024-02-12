package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WipQueueDetailsOutputDTO {
    private Long wipQueueDetailsId;

    private String wipStatusId;

    private String wipStatusName;

    private Long taskId;

    private String taskName;

    private Long objectId;

    private String objectName;

    private Long objectInstanceId;

    private Long wipSetById;

    private String wipSetByName;

    private LocalDate wipSetDateTime;

    private Long assignedById;

    private String assignedByName;

    private Long assignedToId;

    private String assignedToName;

    private LocalDate assignedDate;

    private String assignedStatus;

    private String wipNotes;

    private String assignmentNotes;

    private String assignmentStatusNotes;

    private String objectInstanceIdNo;

    private String state;

    private UUID objectInstanceIdUuid;

    private LocalDate targetedDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID wipQueueDetailsUuid;
}
