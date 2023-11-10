package com.sunknowledge.dme.rcm.web.rest.wip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.service.wip.TaskTypeMasterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/wip/release")
public class TaskTypeMasterController {
	
	@Autowired
	private TaskTypeMasterService taskTypeMasterService;
	
	@ApiOperation(value = "Save Task Type Master")
    @PostMapping(path = "/saveTaskTypeMaster", produces = "application/json")
	public ServiceOutcome<TaskTypeMaster> saveTaskTypeMaster(@RequestParam("Task Name") String taskName, @RequestParam("Object Name") String objectName) {
		
		return taskTypeMasterService.saveTaskTypeMaster(taskName, objectName);
	}
	
	@ApiOperation(value = "Update Task Type Master")
    @PostMapping(path = "/updateTaskTypeMaster", produces = "application/json")
	public ServiceOutcome<TaskTypeMaster> updateTaskTypeMaster(@RequestParam("Task Name") String TaskName, @RequestParam("Object Name") String ObjectName) {
		
		return taskTypeMasterService.updateTaskTypeMaster(TaskName, ObjectName);
	}
	
	@ApiOperation(value = "Get Task Type Master By Object Id")
    @GetMapping(path = "/getTaskTypeMasterByObjectId", produces = "application/json")
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterByObjectId(@RequestParam("Object Id") Long objectId) {
		
		return taskTypeMasterService.getTaskTypeMasterById(objectId);
	}
	
	@ApiOperation(value = "Get Task Type Master By Object Name")
    @GetMapping(path = "/getTaskTypeMasterByObjectName", produces = "application/json")
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterByObjectName(@RequestParam("Object Name") String objectName) {
		
		return taskTypeMasterService.getTaskTypeMasterByName(objectName);
	}
	
	@ApiOperation(value = "Get Task Type Master By Status")
    @GetMapping(path = "/getTaskTypeMasterByStatus", produces = "application/json")
	public ServiceOutcome<List<TaskTypeMaster>> getTaskTypeMasterByStatus(@RequestParam("Status") String status) {
		
		return taskTypeMasterService.getTaskTypeMasterByStatus(status);
	}
	
	@ApiOperation(value = "Get All Task Type Master")
    @GetMapping(path = "/getAllTaskTypeMaster", produces = "application/json")
	public ServiceOutcome<List<TaskTypeMaster>> getAllTaskTypeMaster() {
		
		return taskTypeMasterService.getAllTaskTypeMaster();
	}

}
