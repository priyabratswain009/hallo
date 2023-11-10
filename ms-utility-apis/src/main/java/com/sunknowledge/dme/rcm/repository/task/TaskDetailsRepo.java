package com.sunknowledge.dme.rcm.repository.task;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.repository.TaskDetailsRepository;

public interface TaskDetailsRepo extends TaskDetailsRepository {

	@Query(value = "select * from get_task_no()", nativeQuery = true)
	String getTaskNo();

	@Query(value = "select * from common.task_details_uuid_generator()", nativeQuery = true)
	String gettaskDeatilsUUID();

	@Query(value = "From TaskDetails where taskDetailsId=:taskDetailsId")
	TaskDetails getTaskDetailsByTaskDetailsId(@Param("taskDetailsId") Long taskDetailsId);

	@Query(value = "From TaskDetails where taskDetailsUuid=:taskDetailsUuid")
	TaskDetails getTaskDetailsByUUID(@Param("taskDetailsUuid") UUID taskDetailsUuid);

	@Query(value = "From TaskDetails where taskNo=:taskNo")
	TaskDetails getTaskDetailsByTaskNo(@Param("taskNo") String taskNo);

	@Query(value = "From TaskDetails where taskName=:taskName")
	List<TaskDetails> getTaskDetailsByTaskName(@Param("taskName") String taskName);

	@Query(value = "From TaskDetails where severity=:severity")
	List<TaskDetails> getTaskDetailsBySeverity(@Param("severity") String severity);

	@Query(value = "From TaskDetails where assignedToId=:assignedToId")
	List<TaskDetails> getTaskDetailsByAssignedToId(@Param("assignedToId") Long assignedToId);

	@Query(value = "From TaskDetails where taskState=:taskState")
	List<TaskDetails> getTaskDetailsByTaskState(@Param("taskState") String taskState);

	@Query(value = "From TaskDetails where objectId=:objectId and objectInstanceIdNo=:objectInstanceIdNo")
	List<TaskDetails> getTaskDetailsByObjectandInstanceId(@Param("objectId") Long objectId,
			@Param("objectInstanceIdNo") String objectInstanceIdNo);

	@Query(value = "From TaskDetails where assignedToId=:assignedToId and assignmentDate=:assignmentDate and taskState=:taskState")
	List<TaskDetails> getTaskDetailsByAssignedToIdandDate(@Param("assignedToId") Long assignedToId,
			@Param("assignmentDate") LocalDate assignmentDate, @Param("taskState") String taskState);

	@Query(value = "from TaskDetails where dateNeeded < CURRENT_DATE and dateCompleted IS NULL and taskState='initiated'")
	List<TaskDetails> getpendingTaskDetails();

}
