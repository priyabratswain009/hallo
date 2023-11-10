package com.sunknowledge.dme.rcm.web.rest.wip;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.wip.WIPStateandObject;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import com.sunknowledge.dme.rcm.service.wip.WIPQueueDetailsService;
import com.sunknowledge.dme.rcm.service.wip.dto.WipQueueDetailscustomDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/wip/release")
public class WIPQueueDetailsController {

	@Autowired
	WIPQueueDetailsService wIPQueueDetailsService;

	@ApiOperation(value = "Save WIP Deatils")
	@PostMapping(path = "/saveWIPDeatils", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> saveWIPDeatils(@RequestParam("wipStatusId") Long wipStatusId,
			@RequestParam("taskId") Long taskId, @RequestParam("objectId") Long objectId,
			@RequestParam("objectInstanceId") Long objectInstanceId, @RequestParam("wipSetById") Long wipSetById,
			@RequestParam("wipSetByName") String wipSetByName, @RequestParam("assignedById") Long assignedById,
			@RequestParam("assignedByName") String assignedByName, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("Assigned To Name") String assignedtoName, @RequestParam("assignedDate") String assignedDate,
			@RequestParam("assignedStatus") String assignedStatus, @RequestParam("wipNotes") String wipNotes,
			@RequestParam("assignmentNotes") String assignmentNotes,
			@RequestParam("assignmentStatusNotes") String assignmentStatusNotes,
			@RequestParam("objectInstanceIdNo") String objectInstanceIdNo) {

		return wIPQueueDetailsService.saveObjectTypeMaster(wipStatusId, taskId, objectId, objectInstanceId, wipSetById,
				wipSetByName, assignedById, assignedByName, assignedToId, assignedtoName, assignedDate, assignedStatus,
				wipNotes, assignmentNotes, assignmentStatusNotes, objectInstanceIdNo);
	}

	@ApiOperation(value = "Update WIP Deatils Master")
	@PostMapping(path = "/updateWIPDeatilsMaster", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> updateObjectTypeMaster(
			@RequestParam("wipQueueDetailsId") Long wipQueueDetailsId, @RequestParam("wipStatusId") Long wipStatusId,
			@RequestParam("taskId") Long taskId, @RequestParam("objectInstanceId") Long objectInstanceId,
			@RequestParam("wipSetById") Long wipSetById, @RequestParam("wipSetByName") String wipSetByName,
			@RequestParam("wipSetDateTime") String wipSetDateTime, @RequestParam("assignedById") Long assignedById,
			@RequestParam("assignedByName") String assignedByName, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("Assigned To Name") String assignedtoName, @RequestParam("assignedDate") String assignedDate,
			@RequestParam("assignedStatus") String assignedStatus, @RequestParam("wipNotes") String wipNotes,
			@RequestParam("assignmentNotes") String assignmentNotes,
			@RequestParam("assignmentStatusNotes") String assignmentStatusNotes,
			@RequestParam("objectInstanceIdNo") String objectInstanceIdNo) {

		return wIPQueueDetailsService.updateObjectTypeMaster(wipQueueDetailsId, wipStatusId, taskId, objectInstanceId,
				wipSetById, wipSetByName, wipSetDateTime, assignedById, assignedByName, assignedToId, assignedtoName,
				assignedDate, assignedStatus, wipNotes, assignmentNotes, assignmentStatusNotes, objectInstanceIdNo);
	}

	@ApiOperation(value = "Assign WIP to a user")
	@PostMapping(path = "/assignWIP", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> assignWIP(
			@RequestParam("wipQueueDetailsId") Long wipQueueDetailsId,  @RequestParam("assignedById") Long assignedById,
			@RequestParam("assignedByName") String assignedByName, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("Assigned To Name") String assignedtoName, @RequestParam("assignedDate") String assignedDate,
			@RequestParam("assignedStatus") String assignedStatus, @RequestParam("wipNotes") String wipNotes,
			@RequestParam("assignmentNotes") String assignmentNotes,
			@RequestParam("assignmentStatusNotes") String assignmentStatusNotes) {

		return wIPQueueDetailsService.assignWIP(wipQueueDetailsId, assignedById, assignedByName, assignedToId, assignedtoName,
				assignedDate, assignedStatus, wipNotes, assignmentNotes, assignmentStatusNotes);
	}

	@ApiOperation(value = "Update WIP Details State")
	@PostMapping(path = "/UpdateWIPDetailsState", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> updateWIPDetailsState(
			@RequestParam("wipQueueDetailsId") Long wipQueueDetailsId,  @RequestParam("state") String state) {

		return wIPQueueDetailsService.updateWIPDetailsState(wipQueueDetailsId, state);
	}

	@ApiOperation(value = "WIP Queue Details By Id")
	@GetMapping(path = "/getWIPQueueDetailsByUUId", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByUUId(
			@RequestParam("wipQueueDetailsUUId") UUID wipQueueDetailsUUId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByUUId(wipQueueDetailsUUId);
	}

	@ApiOperation(value = "WIP Queue Details By TargetedDate")
	@GetMapping(path = "/getWIPQueueDetailsByTargetedDate", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByTargetedDate(
			@RequestParam("TargettedDate") String TargettedDate) {

		return wIPQueueDetailsService.getWIPQueueDetailsByTargetedDate(TargettedDate);
	}

	@ApiOperation(value = "WIP Queue Details By WIPStatusId")
	@GetMapping(path = "/getWIPQueueDetailsByWIPStatusId", produces = "application/json")
	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByWIPStatusId(
			@RequestParam("WIPStatusId") Long WIPStatusId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPStatusId(WIPStatusId);
	}

	@ApiOperation(value = "WIP Queue Details By Task Id")
	@GetMapping(path = "/getWIPQueueDetailsByWIPTaskId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskId(@RequestParam("TaskId") Long taskId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPTaskId(taskId);
	}

	@ApiOperation(value = "WIP Queue Details By Task Name")
	@GetMapping(path = "/getWIPQueueDetailsByWIPTaskName", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskName(
			@RequestParam("taskName") String taskName) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPTaskName(taskName);
	}

	@ApiOperation(value = "WIP Queue Details By Object Id")
	@GetMapping(path = "/getWIPQueueDetailsByObjectId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectId(
			@RequestParam("ObjectId") Long objectId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByObjectId(objectId);
	}

	@ApiOperation(value = "WIP Queue Details By Object Instance Id")
	@GetMapping(path = "/getWIPQueueDetailsByObjectInstanceId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectInstanceId(
			@RequestParam("ObjectInstanceId") Long objectInstanceId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByObjectInstanceId(objectInstanceId);
	}

	@ApiOperation(value = "WIP Queue Details By Assigned To Id")
	@GetMapping(path = "/getWIPQueueDetailsByAssignedToId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToId(
			@RequestParam("AssignedToId") Long assignedToId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByAssignedToId(assignedToId);
	}

	@ApiOperation(value = "WIP Queue Details By Assigned To Id and State")
	@GetMapping(path = "/getWIPQueueDetailsByAssignedToIdandState", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToIdandState(
			@RequestParam("AssignedToId") Long assignedToId, @RequestParam("state") String state) {

		return wIPQueueDetailsService.getWIPQueueDetailsByAssignedToIdandState(assignedToId, state);
	}

	@ApiOperation(value = "WIP Queue Details By State")
	@GetMapping(path = "/getWIPQueueDetailsByWIPState", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPState(@RequestParam("State") String state) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPState(state);
	}

	@ApiOperation(value = "WIP Queue Details By State and ObjectId")
	@GetMapping(path = "/getWIPQueueDetailsByWIPStateObjectId", produces = "application/json")
	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateObjectId(
			@RequestParam("State") String state, @RequestParam("taskName") String taskName,
			@RequestParam("ObjectId") Long objectId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPStateandObject(state, taskName, objectId);
	}

	@ApiOperation(value = "WIP Queue Details By Object Instance Id and ObjectId")
	@GetMapping(path = "/getWIPQueueDetailsByWIPObjectInstanceIdandObjectId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(
			@RequestParam("objectInstanceId") Long objectInstanceId, @RequestParam("ObjectId") Long objectId) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(objectInstanceId, objectId);
	}

	@ApiOperation(value = "WIP Queue Details By Object Instance Id and ObjectId and state")
	@GetMapping(path = "/getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdandState", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdandState(
			@RequestParam("objectInstanceId") Long objectInstanceId, @RequestParam("ObjectId") Long objectId,
			@RequestParam("state") String state) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdandState(objectInstanceId,
				objectId, state);
	}

	@ApiOperation(value = "WIP Queue Details By State, ObjectId, taskName and Assigned Date")
	@GetMapping(path = "/getWIPQueueDetailsByWIPStateObjectIdAssignedDate", produces = "application/json")
	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateObjectIdAssignedDate(
			@RequestParam("State") String state, @RequestParam("taskId") Long taskId,
			@RequestParam("ObjectId") Long objectId, @RequestParam("AssignedDate") String assignedDate) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPStateObjectIdAssignedDate(state, taskId, objectId,
				assignedDate);
	}

	@ApiOperation(value = "WIP Queue Details By Created or Assigned Date")
	@GetMapping(path = "/getWIPQueueDetailsByWICreatedAssignedDate", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWICreatedAssignedDate(
			@RequestParam("CreatedDate") String createdDate, @RequestParam("AssignedDate") String assignedDate) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWICreatedAssignedDate(createdDate, assignedDate);
	}

	@ApiOperation(value = "WIP Queue Details By State, ObjectId, wip, assignedtoId and Assigned Date")
	@GetMapping(path = "/getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(
			@RequestParam("objectId") Long objectId, @RequestParam("wipstatId") String wipstatId,
			@RequestParam("state") String state, @RequestParam("assignedtoId") Long assignedtoId,
			@RequestParam("assignedtoDate") String assignedtoDate) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(objectId, wipstatId,
				state, assignedtoId, assignedtoDate);
	}

	@ApiOperation(value = "WIP Queue Details By assignedbyId, wip, assignedtoId and AssignedDate")
	@GetMapping(path = "/getWIPQueueDetailsByWIPasssngIdAssignedDate", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPasssngIdAssignedDate(
			@RequestParam("AssignedDate") String AssignedDate, @RequestParam("wipstatId") String wipstatId,
			@RequestParam("assignedtoId") Long assignedtoId, @RequestParam("assignedById") Long assignedById) {

		return wIPQueueDetailsService.getWIPQueueDetailsByWIPasssngIdAssignedDate(AssignedDate, wipstatId, assignedtoId,
				assignedById);
	}

	@ApiOperation(value = "WIP Queue Details By wipstatusId Date state")
	@GetMapping(path = "/getWIPQueueDetailsForReandAssignment", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReandAssignment(
			@RequestParam("wipstatusId") String wipstatusId, @RequestParam("Date") String date,
			@RequestParam("state") String state) {

		return wIPQueueDetailsService.getWIPQueueDetailsForReandAssignment(wipstatusId, date, state);
	}

	@ApiOperation(value = "WIP Queue Details Reassignment")
	@GetMapping(path = "/getWIPQueueDetailsForReassignment", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReassignment(
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("assignedToName") String assignedToName,
			@RequestParam("wipQueueDetailsUuid") UUID wipQueueDetailsUuid, @RequestParam("Date") String date,
			@RequestParam("state") String state, @RequestParam("AssignmentNotes") String assignmentNotes) {

		return wIPQueueDetailsService.getWIPQueueDetailsForReassignment(assignedToId, assignedToName, wipQueueDetailsUuid, date,
				state, assignmentNotes);
	}

	@ApiOperation(value = "WIP Queue Details Assignment")
	@GetMapping(path = "/getWIPQueueDetailsForAssignment", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForAssignment(
			@RequestParam("wipStatusIdtoAssign") String wipStatusIdtoAssign, @RequestParam("assignedToId") Long assignedToId,
			@RequestParam("objectInstanceIdUuid") UUID objectInstanceIdUuid, @RequestParam("objectInstanceIdNo") String objectInstanceIdNo,
			@RequestParam("assignedToName") String assignedToName, @RequestParam("wipstatusId") String wipstatusId,
			@RequestParam("Date") String date, @RequestParam("state") String state,
			@RequestParam("AssignmentNotes") String assignmentNotes) throws ParseException, org.json.simple.parser.ParseException {

		return wIPQueueDetailsService.getWIPQueueDetailsForAssignment(wipStatusIdtoAssign, assignedToId, objectInstanceIdUuid,
				objectInstanceIdNo, assignedToName, wipstatusId, date, state, assignmentNotes);
	}

	@ApiOperation(value = "WIP Queue Details By ObjectInstanceUUID State ObjectId")
	@GetMapping(path = "/getWIPQueueDetailsForObjectUUIDstateObjectId", produces = "application/json")
	public ServiceOutcome<List<WipQueueDetailscustomDTO>> getWIPQueueDetailsForObjectUUIDstateObjectId(
			@RequestParam("objectInstanceIdUuid") UUID objectInstanceIdUuid, @RequestParam("state") String state,
			@RequestParam("objectId") Long objectId) {

		return wIPQueueDetailsService.getWIPQueueDetailsForObjectUUIDstateObjectId(objectInstanceIdUuid, state, objectId);
	}

}
