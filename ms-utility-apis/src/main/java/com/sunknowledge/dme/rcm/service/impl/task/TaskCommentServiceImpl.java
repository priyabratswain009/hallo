package com.sunknowledge.dme.rcm.service.impl.task;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.repository.task.TaskCommentsRepo;
import com.sunknowledge.dme.rcm.repository.task.TaskDetailsRepo;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskCommentsMapper;
import com.sunknowledge.dme.rcm.service.task.TaskCommentService;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

	@Autowired
	TaskCommentsRepo taskCommentsRepository;
	@Autowired
	TaskDetailsRepo taskDetailsRepository;

	@Autowired
	TaskCommentsMapper taskCommentsMapper;

	@Override
	public ServiceOutcome<TaskCommentsDTO> saveTaskComments(UUID taskDetailsUuid, String commentText, Long commentById,
			String commentDate, String commentByName, Long taskDetailId) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskCommentsDTO> outCome = new ServiceOutcome<TaskCommentsDTO>();
		TaskComments taskComments = new TaskComments();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByTaskDetailsId(taskDetailId);

		if (taskDetails.getTaskState().equalsIgnoreCase("Completed")) {
			outCome.setData(null);
			outCome.setMessage("Task Comments cannot be added if the Task is Completed");
			outCome.setOutcome(false);
		} else {

			if (CommonUtilities.isStringNullOrBlank(taskDetailsUuid.toString())) {
				taskComments.setTaskDetailsUuid(taskDetailsUuid);
			}
			if (CommonUtilities.isStringNullOrBlank(commentText)) {
				taskComments.setCommentText(commentText);
			}
			if (CommonUtilities.isStringNullOrBlank(commentById.toString())) {
				taskComments.setCommentById(commentById);
			}
			if (CommonUtilities.isStringNullOrBlank(commentDate.toString())) {
				taskComments.setCommentDate(CommonUtilities.stringtodateConverter(commentDate));
			}
			if (CommonUtilities.isStringNullOrBlank(commentByName.toString())) {
				taskComments.setCommentByName(commentByName);
			}
			if (CommonUtilities.isStringNullOrBlank(taskDetailId.toString())) {
				taskComments.setTaskDetailsId(taskDetailId);
			}

			taskComments.setStatus("Active");
			taskComments.setCreatedById(Long.valueOf("5501"));
			taskComments.setCreatedByName("Androktasiai");
			taskComments.setCreatedDate(LocalDate.now());

			taskCommentsRepository.save(taskComments);

			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("Data inserted Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskCommentsDTO> updateTaskComments(UUID taskCommentsUuid, UUID taskDetailsUuid,
			String commentText, Long commentById, String commentDate, String commentByName) {
		// TODO Auto-generated method stub
		TaskComments taskComments = taskCommentsRepository.getTaskCommentsByUUID(taskCommentsUuid);
		ServiceOutcome<TaskCommentsDTO> outCome = new ServiceOutcome<TaskCommentsDTO>();

		if (taskComments == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found to Update");
			outCome.setOutcome(false);
		} else {

			taskComments.setStatus("Inactive");
			taskCommentsRepository.save(taskComments);

			if (!(taskDetailsUuid == null)) {
				taskComments.setTaskDetailsUuid(taskDetailsUuid);
			}
			if (CommonUtilities.isStringNullOrBlank(commentText)) {
				taskComments.setCommentText(commentText);
			}
			if (!(commentById == null)) {
				taskComments.setCommentById(commentById);
			}
			if (CommonUtilities.isStringNullOrBlank(commentDate)) {
				taskComments.setCommentDate(CommonUtilities.stringtodateConverter(commentDate));
			}
			if (CommonUtilities.isStringNullOrBlank(commentByName)) {
				taskComments.setCommentByName(commentByName);
			}

			taskComments.setStatus("Active");
			taskComments.setUpdatedById(Long.valueOf("5501"));
			taskComments.setUpdatedByName("Androktasiai");
			taskComments.setUpdatedDate(LocalDate.now());

			taskCommentsRepository.save(taskComments);

			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);

		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskCommentsDTO> getTaskCommentsByUUId(UUID taskCommentsUuid) {
		// TODO Auto-generated method stub
		TaskComments taskComments = taskCommentsRepository.getTaskCommentsByUUID(taskCommentsUuid);
		ServiceOutcome<TaskCommentsDTO> outCome = new ServiceOutcome<TaskCommentsDTO>();

		if (taskComments == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDeatilsUUId(UUID taskDetailsUuid) {
		// TODO Auto-generated method stub
		List<TaskComments> taskComments = taskCommentsRepository.getTaskCommentsByTaskDetailsUUID(taskDetailsUuid);
		ServiceOutcome<List<TaskCommentsDTO>> outCome = new ServiceOutcome<List<TaskCommentsDTO>>();

		if (taskComments == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByCommentedById(Long commentById) {
		// TODO Auto-generated method stub
		List<TaskComments> taskComments = taskCommentsRepository.getTaskCommentsByCommentedById(commentById);
		ServiceOutcome<List<TaskCommentsDTO>> outCome = new ServiceOutcome<List<TaskCommentsDTO>>();

		if (taskComments == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskCommentsDTO>> getTaskCommentsByTaskDetailId(Long taskDetailId) {
		// TODO Auto-generated method stub
		List<TaskComments> taskComments = taskCommentsRepository.getTaskCommentsByTaskDetailId(taskDetailId);
		ServiceOutcome<List<TaskCommentsDTO>> outCome = new ServiceOutcome<List<TaskCommentsDTO>>();

		if (taskComments == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskCommentsMapper.toDto(taskComments));
			outCome.setMessage("No Data Retrieved");
			outCome.setOutcome(true);
		}

		return outCome;
	}

}
