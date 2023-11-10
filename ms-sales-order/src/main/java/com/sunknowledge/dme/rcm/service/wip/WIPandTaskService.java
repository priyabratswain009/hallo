package com.sunknowledge.dme.rcm.service.wip;

import org.json.simple.parser.ParseException;

import reactor.core.publisher.Mono;

public interface WIPandTaskService {
	
	public Mono<Boolean> validateWIP() throws ParseException;
	
	public Mono<Boolean> createSoTasks(String taskName, String objectName) throws ParseException;
	
	public Mono<Boolean> createWIP(Long objectInstanceId, String objectName, Long taskId, String wipStatusName, Long assignedToId, String assignedToName) throws ParseException;

}
