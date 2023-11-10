package com.sunknowledge.dme.rcm.web.rest.wip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.service.wip.WIPQueueOwnerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/wip/release")
public class WIPQueueOwnerController {

	@Autowired
	private WIPQueueOwnerService wIPQueueOwnerService;

	@ApiOperation(value = "Save WIP Queue Owner Master")
	@PostMapping(path = "/saveWIPQueueOwner", produces = "application/json")
	public ServiceOutcome<WipQueueOwner> saveTaskTypeMaster(@RequestParam("WIP Status Name") String WIPStatusName,
			@RequestParam("Task Name") String TaskName, @RequestParam("Assigned By Id") String assignedById,
			@RequestParam("Assigned By Name") String assignedByName,
			@RequestParam("Assigned To Id") String assignedToId,
			@RequestParam("Assigned To Name") String assignedtoName) {

		return wIPQueueOwnerService.saveWIPQueueOwner(WIPStatusName, TaskName, assignedById, assignedByName,
				assignedToId, assignedtoName);
	}

	@ApiOperation(value = "Update WIP Queue Owner Master")
	@PostMapping(path = "/updateWIPQueueOwner", produces = "application/json")
	public ServiceOutcome<WipQueueOwner> updateTaskTypeMaster(@RequestParam("wip Queue Owner Id") Long wipQueueOwnerId,
			@RequestParam("WIP Status Id") String WIPStatusId, @RequestParam("WI Status Name") String WIPStatusName,
			@RequestParam("WIP Status Id") Long taskId, @RequestParam("Task Name") String TaskName,
			@RequestParam("Assigned By Id") String assignedById,
			@RequestParam("Assigned By Name") String assignedByName,
			@RequestParam("Assigned To Id") String assignedToId,
			@RequestParam("Assigned To Name") String assignedtoName,
			@RequestParam("Assigned To Name") String assigneddate) {

		return wIPQueueOwnerService.updateWIPQueueOwner(wipQueueOwnerId, WIPStatusId, WIPStatusName, taskId, TaskName,
				assignedById, assignedByName, assignedToId, assignedtoName, assigneddate);
	}

	@ApiOperation(value = "WIP Queue Owner By Id")
	@GetMapping(path = "/getWIPQueueOwnerById", produces = "application/json")
	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerById(@RequestParam("wipQueueOwnerId") Long wipQueueOwnerId) {

		return wIPQueueOwnerService.getWIPQueueOwnerById(wipQueueOwnerId);
	}

	@ApiOperation(value = "WIP Queue Owner By WIP Status Id")
	@GetMapping(path = "/getWIPQueueOwnerByWIPStatusId", produces = "application/json")
	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerByWIPStatusId(
			@RequestParam("wipStatusId") String wipStatusId) {

		return wIPQueueOwnerService.getWIPQueueOwnerByWIPStatusId(wipStatusId);
	}

	@ApiOperation(value = "WIP Queue Owner By Task Id")
	@GetMapping(path = "/getWIPQueueOwnerByTaskId", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByTaskId(@RequestParam("wipTaskId") Long wipTaskId) {

		return wIPQueueOwnerService.getWIPQueueOwnerByWIPTaskId(wipTaskId);
	}

	@ApiOperation(value = "WIP Queue Owner By WIP Task Name")
	@GetMapping(path = "/getWIPQueueOwnerByTaskName", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByTaskName(
			@RequestParam("wipTaskName") String wipTaskName) {

		return wIPQueueOwnerService.getWIPQueueOwnerByTaskName(wipTaskName);
	}

	@ApiOperation(value = "WIP Queue Owner By Assigned By Id")
	@GetMapping(path = "/getWIPQueueOwnerByAssignedById", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedById(
			@RequestParam("queueOwnerId") Long queueOwner) {

		return wIPQueueOwnerService.getWIPQueueOwnerByAssignedToId(queueOwner);
	}

	@ApiOperation(value = "WIP Queue Owner By Queue Owner Name")
	@GetMapping(path = "/getWIPQueueOwnerByQueueOwnerName", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByQueueOwnerName(
			@RequestParam("queueOwnerName") String queueOwnerName) {

		return wIPQueueOwnerService.getWIPQueueOwnerByQueueOwnerName(queueOwnerName);
	}

	@ApiOperation(value = "WIP Queue Owner By Assigned To Id")
	@GetMapping(path = "/getWIPQueueOwnerByAssignedToId", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedToId(
			@RequestParam("assignedTo") Long assignedToId) {

		return wIPQueueOwnerService.getWIPQueueOwnerByAssignedToId(assignedToId);
	}

	@ApiOperation(value = "Get All WIP Queue Owner By Status")
	@GetMapping(path = "/getWIPQueueOwnerByStatus", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByStatus(@RequestParam("status") String status) {

		return wIPQueueOwnerService.getWIPQueueOwnerByStatus(status);
	}

	@ApiOperation(value = "Get All WIP Queue Owner By ObjectId")
	@GetMapping(path = "/getWIPQueueOwnerByObjectId", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByObjectId(@RequestParam("ObjectId") Long objectId) {

		return wIPQueueOwnerService.getWIPQueueOwnerByObjectId(objectId);
	}

	@ApiOperation(value = "Get All WIP Queue Owner")
	@GetMapping(path = "/getAllWIPQueueOwner", produces = "application/json")
	public ServiceOutcome<List<WipQueueOwner>> getAllWIPQueueOwner() {

		return wIPQueueOwnerService.getAllWIPQueueOwner();
	}

}
