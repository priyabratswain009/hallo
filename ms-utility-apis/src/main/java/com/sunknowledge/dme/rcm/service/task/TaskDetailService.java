package com.sunknowledge.dme.rcm.service.task;

import java.util.List;
import java.util.UUID;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import com.sunknowledge.dme.rcm.service.task.dto.TaskDeatilCommentDTO;

public interface TaskDetailService {

	public ServiceOutcome<String> getTaskNo();

	public ServiceOutcome<TaskDetailsDTO> saveTaskDetail(Long taskId, String taskName, String taskReason,
			String severity, String subjectText, String descriptionText, Long assignedToId, String assignmentDate,
			String dateNeeded, String taskState, String deactiveStatus, Long objectId, String objectName,
			String objectInstanceIdNo, String assignToName, UUID objectInstanceUuid);

	public ServiceOutcome<TaskDetailsDTO> saveTaskDeatilsthroughWIP(Long taskId, String taskReason,String severity,String subjectText,String descriptionText,
			Long assignedToId,String assignmentDate,String dateNeeded,String taskState,String deactiveStatus,Long objectId,
			String objectInstanceIdNo,String assignToName,UUID objectInstanceUuid,Long wipStatusId,String wipStatusName);

	public ServiceOutcome<TaskDetailsDTO> updateTaskDeatils(UUID taskDetailsUuid, String taskNo, Long taskId,
			String taskName, String taskReason, String severity, String subjectText, String descriptionText,
			Long assignedToId, String assignmentDate, String dateNeeded, String dateCompleted, String taskState,
			String deactiveStatus, Long objectId, String objectName, String objectInstanceIdNo, String assignToName,
			UUID objectInstanceUuid);

	public ServiceOutcome<TaskDetailsDTO> updateTaskStateanddeactiveStatus(UUID taskDetailsUuid, String taskState,
			String dateCompleted, String deactiveStatus);

	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByUUId(UUID taskDetailsUuid);

	public ServiceOutcome<TaskDeatilCommentDTO> getTaskDetailsansCommentsByUUId(UUID taskDetailsUuid);

	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByTaskNo(String taskNo);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskName(String taskName);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsBySeverity(String severity);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToId(Long assignedToId);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToIdandDate(Long assignedToId, String dateInput,
			String taskState);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskState(String taskState);

	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByObjectandInstanceId(Long objectId,
			String objectInstanceIdNo);

	public ServiceOutcome<List<TaskDetailsDTO>> getpendingTaskDetails();

	public ServiceOutcome<TaskDetailsDTO> reassignTaskDetails(UUID taskDetailsUuid, Long assignedToId, String dateNeeded,
			String assignToName);

	public ServiceOutcome<TaskDetailsDTO> cancellingTaskDetails(UUID taskDetailsUuid);

}
