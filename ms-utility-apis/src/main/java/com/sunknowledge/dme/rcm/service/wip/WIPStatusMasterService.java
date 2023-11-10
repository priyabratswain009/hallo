package com.sunknowledge.dme.rcm.service.wip;

import java.util.List;
import java.util.UUID;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;

public interface WIPStatusMasterService {

	public ServiceOutcome<WipStatusMaster> createWIPStatusMasterByObjectInstanceId(Long ObjectInstanceId,
			UUID ObjectInstanceUUID, Long ObjectId, Long taskId, String wipStatusName, Long assignedToId,
			String assignedToName);

	public ServiceOutcome<WipStatusMaster> createWIPStatusandWipDetailsasperUI(Long objectInstanceId, String objectName, Long taskId, String wipStatusName,
			Long assignedToId, String assignedToName, String targetedDate);

	public ServiceOutcome<WipStatusMaster> saveWIPStatusMaster(String taskName, String statusName);

	public ServiceOutcome<WipStatusMaster> updateWIPStatusMaster(String statusName, String taskName);

	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterById(Long wipStatusId);

	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterByName(String wipStatusName);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByStatus(String status);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskId(Long taskId);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskName(String taskName);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectId(Long objectId);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectName(String objectName);

	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMaster();

}
