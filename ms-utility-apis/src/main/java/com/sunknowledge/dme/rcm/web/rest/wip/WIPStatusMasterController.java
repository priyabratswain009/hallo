package com.sunknowledge.dme.rcm.web.rest.wip;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.service.wip.WIPStatusMasterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/wip/release")
public class WIPStatusMasterController {

	@Autowired
	private WIPStatusMasterService wIPStatusMasterService;

	@ApiOperation(value = "Create WIP Status Master by Object Instance Id")
	@PostMapping(path = "/createWIPStatusMasterByObjectInstanceId", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> createWIPStatusMasterByObjectInstanceId(
			@RequestParam("ObjectInstanceId") Long ObjectInstanceId,
			@RequestParam("ObjectInstanceUUID") UUID ObjectInstanceUUID, @RequestParam("ObjectId") Long ObjectId,
			@RequestParam("taskId") Long taskId, @RequestParam("WIP Status Name") String wipStatusName,
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("assignedToName") String assignedToName) {

		return wIPStatusMasterService.createWIPStatusMasterByObjectInstanceId(ObjectInstanceId, ObjectInstanceUUID,
				ObjectId, taskId, wipStatusName, assignedToId, assignedToName);
	}

	@ApiOperation(value = "Create WIP Status Master and Status Details as per UI")
	@PostMapping(path = "/createWIPStatusandWipDetailsasperUI", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> createWIPStatusandWipDetailsasperUI(
			@RequestParam("objectInstanceId") Long objectInstanceId, @RequestParam("Object Name") String objectName,
			@RequestParam("taskId") Long taskId, @RequestParam("WIP Status Name") String wipStatusName,
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("assignedToName") String assignedToName, @RequestParam("targetedDate") String targetedDate) {

		return wIPStatusMasterService.createWIPStatusandWipDetailsasperUI(objectInstanceId, objectName, taskId,
				wipStatusName, assignedToId, assignedToName, targetedDate);
	}

	@ApiOperation(value = "Save WIP Status Master")
	@PostMapping(path = "/saveWIPStatusMaster", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> saveWIPStatusMaster(@RequestParam("Task Name") String taskName,
			@RequestParam("WIP Status Name") String wipStatusName) {

		return wIPStatusMasterService.saveWIPStatusMaster(taskName, wipStatusName);
	}

	@ApiOperation(value = "Update WIP Status Master")
	@PostMapping(path = "/updateWIPStatusMaster", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> updateWIPStatusMaster(@RequestParam("Task Name") String TaskName,
			@RequestParam("WIP Status Name") String wipStatusName) {

		return wIPStatusMasterService.updateWIPStatusMaster(TaskName, wipStatusName);
	}

	@ApiOperation(value = "Get WIP Status Master By Id")
	@GetMapping(path = "/getWIPStatusMasterById", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterById(@RequestParam("wip Status Id") Long wipStatusId) {

		return wIPStatusMasterService.getWIPStatusMasterById(wipStatusId);
	}

	@ApiOperation(value = "Get WIP Status Master By Name")
	@GetMapping(path = "/getWIPStatusMasterByName", produces = "application/json")
	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterByName(
			@RequestParam("wip Status Name") String wipStatusName) {

		return wIPStatusMasterService.getWIPStatusMasterByName(wipStatusName);
	}

	@ApiOperation(value = "Get All WIP Master By Status")
	@GetMapping(path = "/getAllWIPStatusMasterByStatus", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByStatus(@RequestParam("Status") String Status) {

		return wIPStatusMasterService.getAllWIPStatusMasterByStatus(Status);

	}

	@ApiOperation(value = "Get All WIP Master By TaskId")
	@GetMapping(path = "/getAllWIPStatusMasterByTaskId", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskId(@RequestParam("TaskId") Long TaskId) {

		return wIPStatusMasterService.getAllWIPStatusMasterByTaskId(TaskId);

	}

	@ApiOperation(value = "Get All WIP Master By TaskName")
	@GetMapping(path = "/getAllWIPStatusMasterByTaskName", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskName(
			@RequestParam("TaskName") String TaskName) {

		return wIPStatusMasterService.getAllWIPStatusMasterByTaskName(TaskName);

	}

	@ApiOperation(value = "Get All WIP Master By Object Id")
	@GetMapping(path = "/getAllWIPStatusMasterByObjectId", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectId(
			@RequestParam("ObjectId") Long ObjectId) {

		return wIPStatusMasterService.getAllWIPStatusMasterByObjectId(ObjectId);

	}

	@ApiOperation(value = "Get All WIP Master By Object Name")
	@GetMapping(path = "/getAllWIPStatusMasterByObjectName", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectName(
			@RequestParam("ObjectName") String ObjectName) {

		return wIPStatusMasterService.getAllWIPStatusMasterByObjectName(ObjectName);

	}

	@ApiOperation(value = "Get All WIP Master")
	@GetMapping(path = "/getAllWIPStatusMaster", produces = "application/json")
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMaster() {

		return wIPStatusMasterService.getAllWIPStatusMaster();
	}

}
