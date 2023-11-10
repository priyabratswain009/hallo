package com.sunknowledge.dme.rcm.web.rest.wip;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.wip.WIPandTaskService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pickupExchange")
public class WIPandTaskResources {

	@Autowired
	private WIPandTaskService wipandTaskService;

	@ApiOperation(value = "Validate WIP services")
	@GetMapping("/validateWIP")
	public Mono<Boolean> validateWIP() throws ParseException {

		return wipandTaskService.validateWIP();
	}

	@ApiOperation(value = "Create SO Task services")
	@PostMapping("/createSoTasks")
	public Mono<Boolean> createSoTasks(@RequestParam("Task Name") String taskName, @RequestParam("Object Name") String objectName) throws ParseException {

		return wipandTaskService.createSoTasks(taskName, objectName);
	}

	@ApiOperation(value = "Create SO Task services")
	@PostMapping("/createWIP")
	public Mono<Boolean> createWIP(
			@RequestParam("objectInstanceId") Long objectInstanceId, @RequestParam("Object Name") String objectName,
			@RequestParam("taskId") Long taskId, @RequestParam("WIP Status Name") String wipStatusName,
			@RequestParam("assignedToId") Long assignedToId, @RequestParam("assignedToName") String assignedToName) throws ParseException {

		return wipandTaskService.createWIP(objectInstanceId, objectName, taskId, wipStatusName, assignedToId, assignedToName);
	}
}
