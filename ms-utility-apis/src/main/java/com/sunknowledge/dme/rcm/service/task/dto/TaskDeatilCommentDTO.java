package com.sunknowledge.dme.rcm.service.task.dto;

import java.util.List;

import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.domain.TaskDetails;

public class TaskDeatilCommentDTO {
	
	private TaskDetails taskDetails;
	
	private List<TaskComments> listTaskComments;

	public TaskDetails getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(TaskDetails taskDetails) {
		this.taskDetails = taskDetails;
	}

	public List<TaskComments> getListTaskComments() {
		return listTaskComments;
	}

	public void setListTaskComments(List<TaskComments> listTaskComments) {
		this.listTaskComments = listTaskComments;
	}

	@Override
	public String toString() {
		return "TaskDeatilCommentDTO [taskDetails=" + taskDetails + ", listTaskComments=" + listTaskComments + "]";
	}
	
	
	
}
