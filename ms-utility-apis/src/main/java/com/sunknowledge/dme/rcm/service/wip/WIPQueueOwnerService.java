package com.sunknowledge.dme.rcm.service.wip;

import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.WipQueueOwner;

public interface WIPQueueOwnerService {

	public ServiceOutcome<WipQueueOwner> saveWIPQueueOwner(String WIPstatusName, String TaskName, String assignedById,
			String assignedByName, String assignedToId, String assignedtoName);

	public ServiceOutcome<WipQueueOwner> updateWIPQueueOwner(Long wipQueueOwnerId, String WIPStatusId, String WIPStatusName, Long taskId,
			String TaskName, String assignedById, String assignedByName, String assignedToId, String assignedtoName,
			String assigneddate);

	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerById(Long wipQueueOwnerId);

	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerByWIPStatusId(String wipStatusId);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByWIPTaskId(Long wipTaskId);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByTaskName(String wipTaskId);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedById(Long queueOwner);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedToId(Long assignedToId);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByQueueOwnerName(String queueOwnerName);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByStatus(String status);

	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByObjectId(Long objectId);

	public ServiceOutcome<List<WipQueueOwner>> getAllWIPQueueOwner();
}
