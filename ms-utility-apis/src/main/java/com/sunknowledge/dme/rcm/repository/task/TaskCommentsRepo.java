package com.sunknowledge.dme.rcm.repository.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.repository.TaskCommentsRepository;

public interface TaskCommentsRepo extends TaskCommentsRepository {

	@Query(value = "From TaskComments where taskDetailsUuid=:taskDetailsUuid")
	List<TaskComments> getTaskCommentsByTaskDetailsUUID(@Param("taskDetailsUuid") UUID taskDetailsUuid);

	@Query(value = "From TaskComments where taskCommentsUuid=:taskCommentsUuid")
	TaskComments getTaskCommentsByUUID(@Param("taskCommentsUuid") UUID taskCommentsUuid);

	@Query(value = "From TaskComments where commentById=:commentById")
	List<TaskComments> getTaskCommentsByCommentedById(@Param("commentById") Long commentById);

	@Query(value = "From TaskComments where taskDetailsId=:taskDetailsId")
	List<TaskComments> getTaskCommentsByTaskDetailId(@Param("taskDetailsId") Long taskDetailsId);
}
