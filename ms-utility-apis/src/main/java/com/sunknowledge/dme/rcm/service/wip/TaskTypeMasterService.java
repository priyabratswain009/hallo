package com.sunknowledge.dme.rcm.service.wip;

import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;

public interface TaskTypeMasterService {

	public ServiceOutcome<TaskTypeMaster> saveTaskTypeMaster(String taskName, String objectName);
	public ServiceOutcome<TaskTypeMaster> updateTaskTypeMaster(String taskName, String objectName);
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterById(Long objectId);
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterByName(String objectName);
	public ServiceOutcome<List<TaskTypeMaster>> getTaskTypeMasterByStatus(String status);
	public ServiceOutcome<List<TaskTypeMaster>> getAllTaskTypeMaster();
	
}
