package com.sunknowledge.dme.rcm.repository.wip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.repository.TaskTypeMasterRepository;

public interface TaskTypeMasterRepo extends TaskTypeMasterRepository{

	@Query(value="From TaskTypeMaster where taskId=:taskId")
	TaskTypeMaster getTaskTypeMasterByTaskId(@Param("taskId") Long taskId);

	@Query(value="From TaskTypeMaster where taskName='patient-profile-clinical-entry'")
	TaskTypeMaster getTaskTypeMasterByName(@Param("taskName") String taskName);

	@Query(value="From TaskTypeMaster where status=:status")
	List<TaskTypeMaster> getTaskTypeMasterByStatus(@Param("status") String status);
	
}
