package com.sunknowledge.dme.rcm.service.wip;

import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;

public interface ObjectTypeMasterService {

	public ServiceOutcome<ObjectTypeMaster> saveObjectTypeMaster(String objectName);
	public ServiceOutcome<ObjectTypeMaster> updateObjectTypeMaster(String objectName);
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterById(Long objectId);
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterByName(String objectName);
	public ServiceOutcome<List<ObjectTypeMaster>> getAllObjectTypeMasterByName();
	public ServiceOutcome<List<ObjectTypeMaster>> getObjectTypeMasterByStatus(String status);
	
}
