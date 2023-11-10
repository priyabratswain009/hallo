package com.sunknowledge.dme.rcm.service.wip;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.wip.WIPStateandObject;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import com.sunknowledge.dme.rcm.service.wip.dto.WipQueueDetailscustomDTO;

public interface WIPQueueDetailsService {

	public ServiceOutcome<WipQueueDetailsDTO> saveObjectTypeMaster(Long wipStatusId, Long taskId, Long objectId,
			Long objectInstanceId, Long wipSetById, String wipSetByName, Long assignedById, String assignedByName,
			Long assignedToId, String assignedtoName, String assignedDate, String assignedStatus, String wipNotes,
			String assignmentNotes, String assignmentStatusNotes, String objectInstanceIdNo);

	public ServiceOutcome<WipQueueDetailsDTO> updateObjectTypeMaster(Long wipQueueDetailsId, Long wipStatusId,
			Long taskId, Long objectInstanceId, Long wipSetById, String wipSetByName, String wipSetDateTime,
			Long assignedById, String assignedByName, Long assignedToId, String assignedtoName, String assignedDate,
			String assignedStatus, String wipNotes, String assignmentNotes, String assignmentStatusNotes,
			String objectInstanceIdNo);

	public ServiceOutcome<WipQueueDetailsDTO> assignWIP(Long wipQueueDetailsId, Long assignedById,
			String assignedByName, Long assignedToId, String assignedtoName, String assignedDate, String assignedStatus,
			String wipNotes, String assignmentNotes, String assignmentStatusNotes);

	public ServiceOutcome<WipQueueDetailsDTO> updateWIPDetailsState(Long wipQueueDetailsId, String state);

	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByUUId(UUID wipQueueDetailsUUId);

	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByWIPStatusId(Long WIPStatusId);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByTargetedDate(String TargettedDate);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskId(Long taskId);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskName(String taskName);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectId(Long objectId);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectInstanceId(Long objectInstanceId);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToId(Long assignedToId);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToIdandState(Long assignedToId,
			String state);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPState(String state);

	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateandObject(String state, String taskName,
			Long Object);

	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateObjectIdAssignedDate(String state,
			Long taskId, Long Object, String assignedDate);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(
			Long ObjectInstanceId, Long Object);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdandState(
			Long ObjectInstanceId, Long Object, String state);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWICreatedAssignedDate(String createdDate,
			String assignedDate);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(
			Long objectId, String wipstatId, String state, Long assignedtoId, String assignedtoDate);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPasssngIdAssignedDate(String AssignedDate,
			String wipstatId, Long assignedtoId, Long assignedById);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReandAssignment(String wipstatusId,
			String date, String state);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReassignment(Long assignedToId,
			String assignedToName, UUID wipQueueDetailsUuid, String date, String state, String assignmentNotes);

	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForAssignment(String wipStatusIdtoAssign,
			Long assignedToId, UUID objectInstanceIdUuid, String objectInstanceIdNo, String assignedToName,
			String wipstatusId, String date, String state, String assignmentNotes)
			throws ParseException, org.json.simple.parser.ParseException;

	public ServiceOutcome<List<WipQueueDetailscustomDTO>> getWIPQueueDetailsForObjectUUIDstateObjectId(
			UUID objectInstanceIdUuid, String state, Long objectId);

}
