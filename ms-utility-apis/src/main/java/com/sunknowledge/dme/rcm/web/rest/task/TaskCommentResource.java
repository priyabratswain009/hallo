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
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import com.sunknowledge.dme.rcm.service.task.TaskCommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/task/comments")
public class TaskCommentResource {

	@Autowired
	TaskCommentService taskCommentService;

	@ApiOperation(value = "Save Task comments")
	@PostMapping(path = "/saveTaskComments", produces = "application/json")
	public ServiceOutcome<TaskCommentsDTO> saveTaskComments(@RequestParam("taskDetailsUuid") UUID taskDetailsUuid,
			@RequestParam("commentText") String commentText, @RequestParam("commentById") Long commentById,
			@RequestParam("commentDate") String commentDate, @RequestParam("commentByName") String commentByName,
			@RequestParam("taskDetailId") Long taskDetailId) {

		return taskCommentService.saveTaskComments(taskDetailsUuid, commentText, commentById, commentDate,
				commentByName, taskDetailId);
	}

	@ApiOperation(value = "Update Task comments")
	@PostMapping(path = "/updateTaskComments", produces = "application/json")
	public ServiceOutcome<TaskCommentsDTO> updateTaskComments(@RequestParam("taskCommentsUuid") UUID taskCommentsUuid,
			@RequestParam("taskDetailsUuid") UUID taskDetailsUuid, @RequestParam("commentText") String commentText,
			@RequestParam("commentById") Long commentById, @RequestParam("commentDate") String commentDate,
			@RequestParam("commentByName") String commentByName) {

		return taskCommentService.updateTaskComments(taskCommentsUuid, taskDetailsUuid, commentText, commentById,
				commentDate, commentByName);
	}

	@ApiOperation(value = "WIP Task Comments By UUID")
	@GetMapping(path = "/getTaskCommentsByUUId", produces = "application/json")
	public ServiceOutcome<TaskCommentsDTO> getTaskCommentsByUUId(
			@RequestParam("taskCommentsUuid") UUID taskCommentsUuid) {

		return taskCommentService.getTaskCommentsByUUId(taskCommentsUuid);
	}

	@ApiOperation(value = "WIP Task Comments By TaskDeatilsUUID")
	@GetMapping(path = "/getTaskCommentsByTaskDeatilsUUId", produces = "application/json")
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDeatilsUUId(
			@RequestParam("taskDetailsUuid") UUID taskDetailsUuid) {

		return taskCommentService.getTaskCommentsByTaskDeatilsUUId(taskDetailsUuid);
	}

	@ApiOperation(value = "WIP Task Comments By CommentedById")
	@GetMapping(path = "/getTaskCommentsByCommentedById", produces = "application/json")
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByCommentedById(
			@RequestParam("commentById") Long commentById) {

		return taskCommentService.getTaskCommentsByCommentedById(commentById);
	}

	@ApiOperation(value = "WIP Task Comments By TaskDetailId")
	@GetMapping(path = "/getTaskCommentsByTaskDetailId", produces = "application/json")
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDetailId(
			@RequestParam("taskDetailId") Long taskDetailId) {

		return taskCommentService.getTaskCommentsByTaskDetailId(taskDetailId);
	}

}
