package com.sunknowledge.dme.rcm.web.rest.task;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import com.sunknowledge.dme.rcm.service.task.TaskDetailService;
import com.sunknowledge.dme.rcm.service.task.dto.TaskDeatilCommentDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/task/release")
public class TaskDetailResource {

	@Autowired
	TaskDetailService taskDetailService;

	@ApiOperation(value = "Save Task Deatils")
	@PostMapping(path = "/saveTaskDeatils", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> saveTaskDeatils(@RequestParam("taskId") Long taskId,
			@RequestParam("taskName") String taskName, @RequestParam("taskReason") String taskReason,
			@RequestParam("severity") String severity, @RequestParam("subjectText") String subjectText,
			@RequestParam("descriptionText") String descriptionText, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("assignmentDate") String assignmentDate, @RequestParam("dateNeeded") String dateNeeded,
			@RequestParam("taskState") String taskState, @RequestParam("deactiveStatus") String deactiveStatus,
			@RequestParam("objectId") Long objectId, @RequestParam("objectName") String objectName,
			@RequestParam("objectInstanceId") String objectInstanceIdNo,
			@RequestParam("assignToName") String assignToName,
			@RequestParam("objectInstanceUuid") UUID objectInstanceUuid) {

		return taskDetailService.saveTaskDetail(taskId, taskName, taskReason, severity, subjectText, descriptionText,
				assignedToId, assignmentDate, dateNeeded, taskState, deactiveStatus, objectId, objectName,
				objectInstanceIdNo, assignToName, objectInstanceUuid);
	}

	@ApiOperation(value = "Save Task Deatils")
	@PostMapping(path = "/saveTaskDeatilsthroughWIP", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> saveTaskDeatilsthroughWIP(@RequestParam("taskId") Long taskId,
			@RequestParam("taskReason") String taskReason, @RequestParam("severity") String severity,
			@RequestParam("subjectText") String subjectText, @RequestParam("descriptionText") String descriptionText,
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("assignmentDate") String assignmentDate,
			@RequestParam("dateNeeded") String dateNeeded, @RequestParam("taskState") String taskState,
			@RequestParam("deactiveStatus") String deactiveStatus, @RequestParam("objectId") Long objectId,
			@RequestParam("objectInstanceId") String objectInstanceIdNo,
			@RequestParam("assignToName") String assignToName,
			@RequestParam("objectInstanceUuid") UUID objectInstanceUuid, @RequestParam("wipStatusId") Long wipStatusId,
			@RequestParam("wipStatusName") String wipStatusName) {

		return taskDetailService.saveTaskDeatilsthroughWIP(taskId, taskReason, severity, subjectText, descriptionText,
				assignedToId, assignmentDate, dateNeeded, taskState, deactiveStatus, objectId,
				objectInstanceIdNo, assignToName, objectInstanceUuid, wipStatusId, wipStatusName);
	}

	@ApiOperation(value = "Update Task Details")
	@PostMapping(path = "/updateTaskDetails", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> updateTaskDetails(@RequestParam("taskDetailsUuid") UUID taskDetailsUuid,
			@RequestParam("taskNo") String taskNo, @RequestParam("taskId") Long taskId,
			@RequestParam("taskName") String taskName, @RequestParam("taskReason") String taskReason,
			@RequestParam("severity") String severity, @RequestParam("subjectText") String subjectText,
			@RequestParam("descriptionText") String descriptionText, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("assignmentDate") String assignmentDate, @RequestParam("dateNeeded") String dateNeeded,
			@RequestParam("dateCompleted") String dateCompleted, @RequestParam("taskState") String taskState,
			@RequestParam("deactiveStatus") String deactiveStatus, @RequestParam("objectId") Long objectId,
			@RequestParam("objectName") String objectName, @RequestParam("objectInstanceId") String objectInstanceIdNo,
			@RequestParam("assignToName") String assignToName, @RequestParam("assignToName") UUID objectInstanceUuid) {

		return taskDetailService.updateTaskDeatils(taskDetailsUuid, taskNo, taskId, taskName, taskReason, severity,
				subjectText, descriptionText, assignedToId, assignmentDate, dateNeeded, dateCompleted, taskState,
				deactiveStatus, objectId, objectName, objectInstanceIdNo, assignToName, objectInstanceUuid);
	}

	@ApiOperation(value = "Update Task State")
	@PostMapping(path = "/updateTaskState", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> updateTaskStateanddeactiveStatus(
			@RequestParam("taskDetailsUuid") UUID taskDetailsUuid, @RequestParam("taskState") String taskState,
			@RequestParam("dateCompleted") String dateCompleted,
			@RequestParam("deactiveStatus") String deactiveStatus) {

		return taskDetailService.updateTaskStateanddeactiveStatus(taskDetailsUuid, taskState, dateCompleted,
				deactiveStatus);
	}

	@ApiOperation(value = "WIP Task Details By UUID")
	@GetMapping(path = "/getTaskDetailsByUUId", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByUUId(@RequestParam("taskDetailsUuid") UUID taskDetailsUuid) {

		return taskDetailService.getTaskDetailsByUUId(taskDetailsUuid);
	}

	@ApiOperation(value = "WIP Task Details and TaskComments By UUID")
	@GetMapping(path = "/getTaskDetailsansCommentsByUUId", produces = "application/json")
	public ServiceOutcome<TaskDeatilCommentDTO> getTaskDetailsansCommentsByUUId(
			@RequestParam("taskDetailsUuid") UUID taskDetailsUuid) {

		return taskDetailService.getTaskDetailsansCommentsByUUId(taskDetailsUuid);
	}

	@ApiOperation(value = "WIP Task Details By Task No")
	@GetMapping(path = "/getTaskDetailsByTaskNo", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByTaskNo(@RequestParam("taskNo") String taskNo) {

		return taskDetailService.getTaskDetailsByTaskNo(taskNo);
	}

	@ApiOperation(value = "WIP Task Details By Task Name")
	@GetMapping(path = "/getTaskDetailsByTaskName", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskName(@RequestParam("taskName") String taskName) {

		return taskDetailService.getTaskDetailsByTaskName(taskName);
	}

	@ApiOperation(value = "WIP Task Details By Severity")
	@GetMapping(path = "/getTaskDetailsBySeverity", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsBySeverity(@RequestParam("severity") String severity) {

		return taskDetailService.getTaskDetailsBySeverity(severity);
	}

	@ApiOperation(value = "WIP Task Details By AssignedToId")
	@GetMapping(path = "/getTaskDetailsByAssignedToId", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToId(
			@RequestParam("assignedToId") Long assignedToId) {

		return taskDetailService.getTaskDetailsByAssignedToId(assignedToId);
	}

	@ApiOperation(value = "WIP Task Details By AssignedToId")
	@GetMapping(path = "/getTaskDetailsByAssignedToIdandDate", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToIdandDate(
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("dateInput") String dateInput,
			@RequestParam("taskState") String taskState) {

		return taskDetailService.getTaskDetailsByAssignedToIdandDate(assignedToId, dateInput, taskState);
	}

	@ApiOperation(value = "WIP Task Details By Task State")
	@GetMapping(path = "/getTaskDetailsByTaskState", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskState(@RequestParam("taskState") String taskState) {

		return taskDetailService.getTaskDetailsByTaskState(taskState);
	}

	@ApiOperation(value = "WIP Task Details By ObjectID and ObjectInstanceIdNo")
	@GetMapping(path = "/getTaskDetailsByObjectandInstanceId", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByObjectandInstanceId(
			@RequestParam("objectId") Long objectId, @RequestParam("objectInstanceIdNo") String objectInstanceIdNo) {

		return taskDetailService.getTaskDetailsByObjectandInstanceId(objectId, objectInstanceIdNo);
	}

	@ApiOperation(value = "WIP Task Details Pending")
	@GetMapping(path = "/getpendingTaskDetails", produces = "application/json")
	public ServiceOutcome<List<TaskDetailsDTO>> getpendingTaskDetails() {

		return taskDetailService.getpendingTaskDetails();
	}

	@ApiOperation(value = "Reassign Task Details")
	@PostMapping(path = "/reassignTaskDetails", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> reassignTaskDetails(@RequestParam("taskDetailsUuid") UUID taskDetailsUuid,
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("dateNeeded") String dateNeeded,
			@RequestParam("assignToName") String assignToName) {

		return taskDetailService.reassignTaskDetails(taskDetailsUuid, assignedToId, dateNeeded, assignToName);
	}

	@ApiOperation(value = "Cancelling Task Details")
	@PostMapping(path = "/cancellingTaskDetails", produces = "application/json")
	public ServiceOutcome<TaskDetailsDTO> cancellingTaskDetails(@RequestParam("taskDetailsUuid") UUID taskDetailsUuid) {

		return taskDetailService.cancellingTaskDetails(taskDetailsUuid);
	}

}
