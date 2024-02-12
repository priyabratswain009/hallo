package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WipStatusUpdateInfoParameterDTO {
    @NotNull(message = "SO_UUID must be provided.")
    private UUID soUuid;

    //    @NotNull(message = "WIP_Status_Name must be provided.")
//    @NotBlank(message = "WIP_Status_Name must be provided.")
    private String wipStatusName;
    //    @NotNull(message = "Task_Name must be provided.")
//    @NotBlank(message = "Task_Name must be provided.")
    private String taskName;
//    @NotNull(message = "Object_Id must be provided.")
//    @Min(value = 1, message = "Object_Id must be greater than and equal to 1 (One).")
//    private Long objectId;

    //------ Object_Instance_Id_No ------------------------------
    @NotNull(message = "SO_Id_No must be provided.")
    @NotBlank(message = "SO_Id_No must be provided.")
    private String soIdNo;
//    @NotNull(message = "State must be provided.")
//    @NotBlank(message = "State must be provided.")
//    private String state;

    //    @NotNull(message = "Assigned_To must be provided.")
//    @Min(value = 1, message = "Assigned_To must be greater than and equal to 1 (One).")
    private Long assignedToId;
    //    private String assignedToName;
//    private String assignedStatus;
//    private String assignmentNotes;
//    private String assignmentStatusNotes;
//    private String wipNotes;
//    private LocalDate dateNeeded;
}
