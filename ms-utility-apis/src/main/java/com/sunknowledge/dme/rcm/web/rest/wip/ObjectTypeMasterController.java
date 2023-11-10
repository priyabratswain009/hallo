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
import com.sunknowledge.dme.rcm.service.wip.ObjectTypeMasterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/wip/release")
public class ObjectTypeMasterController {

	@Autowired
	private ObjectTypeMasterService objectTypeMasterService;

	/*
	 * Decided on the fact that Object Type changes will only be done through BackEnd
	 * @ApiOperation(value = "Save Object Type Master")
	 * 
	 * @PostMapping(path = "/saveObjectTypeMaster", produces = "application/json")
	 * public ServiceOutcome<ObjectTypeMaster>
	 * saveObjectTypeMaster(@RequestParam("Object Name") String objectName) {
	 * 
	 * return objectTypeMasterService.saveObjectTypeMaster(objectName); }
	 * 
	 * @ApiOperation(value = "Update Object Type Master")
	 * 
	 * @PostMapping(path = "/updateObjectTypeMaster", produces = "application/json")
	 * public ServiceOutcome<ObjectTypeMaster>
	 * updateObjectTypeMaster(@RequestParam("Object Name") String objectName) {
	 * 
	 * return objectTypeMasterService.updateObjectTypeMaster(objectName); }
	 */

	@ApiOperation(value = "Get Object Type Master By Id")
	@GetMapping(path = "/getObjectTypeMasterById", produces = "application/json")
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterById(@RequestParam("Object Id") Long objectId) {

		return objectTypeMasterService.getObjectTypeMasterById(objectId);
	}

	@ApiOperation(value = "Get Object Type Master By Name")
	@GetMapping(path = "/getObjectTypeMasterByName", produces = "application/json")
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterByName(@RequestParam("Object Name") String objectName) {

		return objectTypeMasterService.getObjectTypeMasterByName(objectName);
	}

	@ApiOperation(value = "Get Object Type Master By status")
	@GetMapping(path = "/getObjectTypeMasterBystatus", produces = "application/json")
	public ServiceOutcome<List<ObjectTypeMaster>> getObjectTypeMasterBystatus(@RequestParam("status") String status) {

		return objectTypeMasterService.getObjectTypeMasterByStatus(status);
	}

	@ApiOperation(value = "Get All Object Type Master")
	@GetMapping(path = "/getAllObjectTypeMaster", produces = "application/json")
	public ServiceOutcome<List<ObjectTypeMaster>> getAllObjectTypeMasterByName() {

		return objectTypeMasterService.getAllObjectTypeMasterByName();
	}

}
