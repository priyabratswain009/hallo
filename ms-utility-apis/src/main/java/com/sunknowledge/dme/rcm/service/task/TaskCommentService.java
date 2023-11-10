package com.sunknowledge.dme.rcm.service.task;

import java.util.List;
import java.util.UUID;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;

public interface TaskCommentService {

	public ServiceOutcome<TaskCommentsDTO> saveTaskComments(UUID taskDetailsUuid, String commentText, Long commentById,
			String commentDate, String commentByName, Long taskDetailId);

	public ServiceOutcome<TaskCommentsDTO> updateTaskComments(UUID taskCommentsUuid, UUID taskDetailsUuid, String commentText, Long commentById,
			String commentDate, String commentByName);

	public ServiceOutcome<TaskCommentsDTO> getTaskCommentsByUUId(UUID taskCommentsUuid);

	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDeatilsUUId(UUID taskDetailsUuid);

	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByCommentedById(Long commentById);

	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDetailId(Long taskDetailId);

}
