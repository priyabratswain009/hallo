package com.sunknowledge.dme.rcm.dto.wip;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Duration", "ObjectId", "ObjectName", "taskName", "wipStatusName", "wipStatusId", "assignedToId",
		"assignedToName", "state" })
@Generated("jsonschema2pojo")
public class WIPStateandObject {

	@JsonProperty("Duration")
	private String duration;
	@JsonProperty("ObjectId")
	private Long objectId;
	@JsonProperty("ObjectName")
	private String objectName;
	@JsonProperty("taskName")
	private String taskName;
	@JsonProperty("wipStatusName")
	private String wipStatusName;
	@JsonProperty("wipStatusId")
	private String wipStatusId;
	@JsonProperty("assignedToId")
	private Long assignedToId;
	@JsonProperty("assignedToName")
	private String assignedToName;
	@JsonProperty("state")
	private String state;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("Duration")
	public String getDuration() {
		return duration;
	}

	@JsonProperty("Duration")
	public void setDuration(String duration) {
		this.duration = duration;
	}

	@JsonProperty("ObjectId")
	public Long getObjectId() {
		return objectId;
	}

	@JsonProperty("ObjectId")
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@JsonProperty("ObjectName")
	public String getObjectName() {
		return objectName;
	}

	@JsonProperty("ObjectName")
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@JsonProperty("taskName")
	public String getTaskName() {
		return taskName;
	}

	@JsonProperty("taskName")
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@JsonProperty("wipStatusName")
	public String getWipStatusName() {
		return wipStatusName;
	}

	@JsonProperty("wipStatusName")
	public void setWipStatusName(String wipStatusName) {
		this.wipStatusName = wipStatusName;
	}

	@JsonProperty("wipStatusId")
	public String getWipStatusId() {
		return wipStatusId;
	}

	@JsonProperty("wipStatusId")
	public void setWipStatusId(String wipStatusId) {
		this.wipStatusId = wipStatusId;
	}

	@JsonProperty("assignedToId")
	public Long getAssignedToId() {
		return assignedToId;
	}

	@JsonProperty("assignedToId")
	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}

	@JsonProperty("assignedToName")
	public String getAssignedToName() {
		return assignedToName;
	}

	@JsonProperty("assignedToName")
	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}