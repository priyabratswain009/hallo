package com.sunknowledge.dme.rcm.service.wip.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;

public class WipQueueDetailscustomDTO {

	private Long assignedToId;

	private String assignedToName;

	private LocalDate assignedDate;

	private Long assignedById;

	private String assignedByName;

	private String wipNotes;

	private String assignmentNotes;

	private String assignmentStatusNotes;

	private String duration;

	public Long getAssignedById() {
		return assignedById;
	}

	public void setAssignedById(Long assignedById) {
		this.assignedById = assignedById;
	}

	public String getAssignedByName() {
		return assignedByName;
	}

	public void setAssignedByName(String assignedByName) {
		this.assignedByName = assignedByName;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public void setAssignedToName(String assignedToName) {
		this.assignedToName = assignedToName;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getWipNotes() {
		return wipNotes;
	}

	public void setWipNotes(String wipNotes) {
		this.wipNotes = wipNotes;
	}

	public String getAssignmentNotes() {
		return assignmentNotes;
	}

	public void setAssignmentNotes(String assignmentNotes) {
		this.assignmentNotes = assignmentNotes;
	}

	public String getAssignmentStatusNotes() {
		return assignmentStatusNotes;
	}

	public void setAssignmentStatusNotes(String assignmentStatusNotes) {
		this.assignmentStatusNotes = assignmentStatusNotes;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "WipQueueDetailsDTO{" + ", assignedById=" + getAssignedById() + ", assignedByName='"
				+ getAssignedByName() + "'" + ", assignedToId=" + getAssignedToId() + ", assignedToName='"
				+ getAssignedToName() + "'" + ", assignedDate='" + getAssignedDate() + "'" + ", wipNotes='"
				+ getWipNotes() + "'" + ", assignmentNotes='" + getAssignmentNotes() + "'" + ", assignmentStatusNotes='"
				+ getAssignmentStatusNotes() + "'" + ", duration='" + getDuration() + "'" + "}";
	}
}
