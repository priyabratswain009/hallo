package com.sunknowledge.dme.rcm.service.impl.task;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.repository.task.TaskCommentsRepo;
import com.sunknowledge.dme.rcm.repository.task.TaskDetailsRepo;
import com.sunknowledge.dme.rcm.repository.wip.ObjectTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.TaskTypeMasterRepo;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaskDetailsMapper;
import com.sunknowledge.dme.rcm.service.task.TaskDetailService;
import com.sunknowledge.dme.rcm.service.task.dto.TaskDeatilCommentDTO;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {

	@Autowired
	TaskDetailsMapper taskDetailsMapper;
	@Autowired
	TaskDetailsRepo taskDetailsRepository;
	@Autowired
	ObjectTypeMasterRepo objectTypeMasterRepository;
	@Autowired
	TaskTypeMasterRepo taskTypeMasterRepo;
	@Autowired
	TaskCommentsRepo taskCommentsRepository;

	@Override
	public ServiceOutcome<String> getTaskNo() {
		// TODO Auto-generated method stub

		ServiceOutcome<String> outCome = new ServiceOutcome<String>();

		String taskNo = taskDetailsRepository.getTaskNo();

		if (CommonUtilities.isStringNullOrBlank(taskNo)) {
			outCome.setData(taskNo);
			outCome.setOutcome(true);
		} else {
			outCome.setData(null);
			outCome.setMessage("No Task Number was Found");
			outCome.setOutcome(false);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> saveTaskDetail(Long taskId, String taskName, String taskReason,
			String severity, String subjectText, String descriptionText, Long assignedToId, String assignmentDate,
			String dateNeeded, String taskState, String deactiveStatus, Long objectId, String objectName,
			String objectInstanceIdNo, String assignToName, UUID objectInstanceUuid) {
		// TODO Auto-generated method stub

		TaskDetails taskDetails = new TaskDetails();
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
			taskDetails.setTaskId(taskId);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
			taskDetails.setTaskId(taskId);
		}

		taskDetails.setTaskNo(getTaskNo().getData());

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskName))) {
			taskDetails.setTaskName(taskName);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskReason))) {
			taskDetails.setTaskReason(taskReason);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(severity))) {
			taskDetails.setSeverity(severity);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(subjectText))) {
			taskDetails.setSubjectText(subjectText);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(descriptionText))) {
			taskDetails.setDescriptionText(descriptionText);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedToId))) {
			taskDetails.setAssignedToId(assignedToId);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignmentDate))) {
			taskDetails.setAssignmentDate(CommonUtilities.stringtodateConverter(assignmentDate));
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(dateNeeded))) {
			taskDetails.setDateNeeded(CommonUtilities.stringtodateConverter(dateNeeded));
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskState))) {
			taskDetails.setTaskState(taskState);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(deactiveStatus))) {
			taskDetails.setDeactiveStatus(deactiveStatus);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectId))) {
			taskDetails.setObjectId(objectId);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectName))) {
			taskDetails.setObjectName(objectName);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceIdNo))) {
			taskDetails.setObjectInstanceIdNo(objectInstanceIdNo);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignToName))) {
			taskDetails.setAssignToName(assignToName);
		}

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceUuid))) {
			taskDetails.setObjectInstanceUuid(objectInstanceUuid);
		}

		UUID uuid = UUID.randomUUID();
		taskDetails.setTaskDetailsUuid(uuid);

		taskDetails.setStatus("Active");
		taskDetails.setCreatedById(Long.valueOf("5501"));
		taskDetails.setCreatedByName("Androktasiai");
		taskDetails.setCreatedDate(LocalDate.now());

		taskDetailsRepository.save(taskDetails);

		outCome.setData(taskDetailsMapper.toDto(taskDetails));
		outCome.setMessage("Data Inserted Successfully");
		outCome.setOutcome(true);

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> updateTaskDeatils(UUID taskDetailsUuid, String taskNo, Long taskId,
			String taskName, String taskReason, String severity, String subjectText, String descriptionText,
			Long assignedToId, String assignmentDate, String dateNeeded, String dateCompleted, String taskState,
			String deactiveStatus, Long objectId, String objectName, String objectInstanceIdNo, String assignToName,
			UUID objectInstanceUuid) {
		// TODO Auto-generated method stub

		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {

			taskDetails.setStatus("Inactive");
			taskDetailsRepository.save(taskDetails);

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskNo))) {
				taskDetails.setTaskNo(taskNo);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
				taskDetails.setTaskId(taskId);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskName))) {
				taskDetails.setTaskName(taskName);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskReason))) {
				taskDetails.setTaskReason(taskReason);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(severity))) {
				taskDetails.setSeverity(severity);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(subjectText))) {
				taskDetails.setSubjectText(subjectText);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(descriptionText))) {
				taskDetails.setDescriptionText(descriptionText);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedToId))) {
				taskDetails.setAssignedToId(assignedToId);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignmentDate))) {
				taskDetails.setAssignmentDate(CommonUtilities.stringtodateConverter(assignmentDate));
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(dateNeeded))) {
				taskDetails.setDateNeeded(CommonUtilities.stringtodateConverter(dateNeeded));
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(dateCompleted))) {
				taskDetails.setDateCompleted(CommonUtilities.stringtodateConverter(dateCompleted));
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskState))) {
				taskDetails.setTaskState(taskState);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(deactiveStatus))) {
				taskDetails.setDeactiveStatus(deactiveStatus);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectId))) {
				taskDetails.setObjectId(objectId);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectName))) {
				taskDetails.setObjectName(objectName);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceIdNo))) {
				taskDetails.setObjectInstanceIdNo(objectInstanceIdNo);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignToName))) {
				taskDetails.setAssignToName(assignToName);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceUuid))) {
				taskDetails.setObjectInstanceUuid(objectInstanceUuid);
			}

			taskDetails.setStatus("Active");
			taskDetails.setUpdatedById(Long.valueOf("5501"));
			taskDetails.setUpdatedByName("Androktasiai");
			taskDetails.setUpdatedDate(LocalDate.now());

			taskDetailsRepository.save(taskDetails);

			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByUUId(UUID taskDetailsUuid) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> getTaskDetailsByTaskNo(String taskNo) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByTaskNo(taskNo);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskName(String taskName) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsByTaskName(taskName);

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsBySeverity(String severity) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsBySeverity(severity);
		// String uuId = taskDetailsRepository.gettaskDeatilsUUID();
		UUID uuid = UUID.randomUUID();
		System.out.println("UUID========" + uuid);

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToId(Long assignedToId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsByAssignedToId(assignedToId);

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByTaskState(String taskState) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		System.out.println("Task State Start");
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsByTaskState(taskState);
		System.out.println("Task State End");

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByObjectandInstanceId(Long objectId,
			String objectInstanceIdNo) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsByObjectandInstanceId(objectId,
				objectInstanceIdNo);

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> updateTaskStateanddeactiveStatus(UUID taskDetailsUuid, String taskState,
			String dateCompleted, String deactiveStatus) {
		// TODO Auto-generated method stub

		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found to Update");
			outCome.setOutcome(false);
		} else {

			taskDetails.setStatus("Inactive");
			taskDetailsRepository.save(taskDetails);

			if (CommonUtilities.isStringNullOrBlank(taskState)) {
				taskDetails.setTaskState(taskState);
			}

			if (CommonUtilities.isStringNullOrBlank(deactiveStatus)) {
				taskDetails.setDeactiveStatus(deactiveStatus);
			}

			if (CommonUtilities.isStringNullOrBlank(dateCompleted)) {
				taskDetails.setDateCompleted(CommonUtilities.stringtodateConverter(dateCompleted));
			}

			taskDetails.setStatus("Active");
			taskDetails.setUpdatedById(Long.valueOf("5501"));
			taskDetails.setUpdatedByName("Androktasiai");
			taskDetails.setUpdatedDate(LocalDate.now());

			taskDetailsRepository.save(taskDetails);

			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getTaskDetailsByAssignedToIdandDate(Long assignedToId, String dateInput,
			String taskState) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getTaskDetailsByAssignedToIdandDate(assignedToId,
				CommonUtilities.stringtodateConverter(dateInput), taskState);

		if (taskDetails == null || taskDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDeatilCommentDTO> getTaskDetailsansCommentsByUUId(UUID taskDetailsUuid) {

		// TODO Auto-generated method stub
		ServiceOutcome<TaskDeatilCommentDTO> outCome = new ServiceOutcome<TaskDeatilCommentDTO>();
		TaskDeatilCommentDTO taskDeatilCommentDTO = new TaskDeatilCommentDTO();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);
		List<TaskComments> listTaskComments = taskCommentsRepository.getTaskCommentsByTaskDetailsUUID(taskDetailsUuid);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			taskDeatilCommentDTO.setTaskDetails(taskDetails);
			taskDeatilCommentDTO.setListTaskComments(listTaskComments);

			outCome.setData(taskDeatilCommentDTO);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<TaskDetailsDTO>> getpendingTaskDetails() {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskDetailsDTO>> outCome = new ServiceOutcome<List<TaskDetailsDTO>>();
		List<TaskDetails> taskDetails = taskDetailsRepository.getpendingTaskDetails();

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> reassignTaskDetails(UUID taskDetailsUuid, Long assignedToId,
			String dateNeeded, String assignToName) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {

			taskDetails.setStatus("Inactive");
			taskDetailsRepository.save(taskDetails);

			if (!(assignedToId == null)) {
				taskDetails.setAssignedToId(assignedToId);
			}

			if (CommonUtilities.isStringNullOrBlank(dateNeeded)) {
				taskDetails.setDateNeeded(CommonUtilities.stringtodateConverter(dateNeeded));
			}

			if (CommonUtilities.isStringNullOrBlank(assignToName)) {
				taskDetails.setAssignToName(assignToName);
			}

			taskDetails.setUpdatedById(Long.valueOf("5501"));
			taskDetails.setUpdatedByName("Androktasiai");
			taskDetails.setUpdatedDate(LocalDate.now());
			taskDetails.setAssignmentDate(LocalDate.now());
			taskDetails.setStatus("Active");
			taskDetailsRepository.save(taskDetails);

			outCome.setData(taskDetailsMapper.toDto(taskDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return null;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> cancellingTaskDetails(UUID taskDetailsUuid) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();
		TaskDetails taskDetails = taskDetailsRepository.getTaskDetailsByUUID(taskDetailsUuid);

		if (taskDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {

			if (taskDetails.getTaskState().equalsIgnoreCase("Cancelled")) {

				outCome.setData(null);
				outCome.setMessage("Data Retrieved is already in a Cancelled state");
				outCome.setOutcome(true);

			} else {

				taskDetails.setStatus("Inactive");
				taskDetailsRepository.save(taskDetails);

				taskDetails.setTaskState("Cancelled");

				taskDetails.setStatus("Active");
				taskDetailsRepository.save(taskDetails);

				outCome.setData(taskDetailsMapper.toDto(taskDetails));
				outCome.setMessage("Data Retrieved Successfully");
				outCome.setOutcome(true);
			}
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<TaskDetailsDTO> saveTaskDeatilsthroughWIP(Long taskId, String taskReason, String severity,
			String subjectText, String descriptionText, Long assignedToId, String assignmentDate, String dateNeeded,
			String taskState, String deactiveStatus, Long objectId, String objectInstanceIdNo, String assignToName,
			UUID objectInstanceUuid, Long wipStatusId, String wipStatusName) {
		// TODO Auto-generated method stub

		TaskDetails taskDetails = new TaskDetails();
		ObjectTypeMaster objectTypeMaster = objectTypeMasterRepository.getObjectTypeMasterByobjectId(objectId);
		TaskTypeMaster taskTypeMaster = taskTypeMasterRepo.getTaskTypeMasterByTaskId(taskId);
		ServiceOutcome<TaskDetailsDTO> outCome = new ServiceOutcome<TaskDetailsDTO>();

		taskDetails.setTaskNo(getTaskNo().getData());
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
			taskDetails.setTaskId(taskId);
		}
		if (taskTypeMaster != null) {
			taskDetails.setTaskName(taskTypeMaster.getTaskName());
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskReason))) {
			taskDetails.setTaskReason(taskReason);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(severity))) {
			taskDetails.setSeverity(severity);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(subjectText))) {
			taskDetails.setSubjectText(subjectText);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(descriptionText))) {
			taskDetails.setDescriptionText(descriptionText);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedToId))) {
			taskDetails.setAssignedToId(assignedToId);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignmentDate))) {
			taskDetails.setAssignmentDate(CommonUtilities.stringtodateConverter(assignmentDate));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(dateNeeded))) {
			taskDetails.setDateNeeded(CommonUtilities.stringtodateConverter(dateNeeded));
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskState))) {
			taskDetails.setTaskState(taskState);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(deactiveStatus))) {
			taskDetails.setDeactiveStatus(deactiveStatus);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectId))) {
			taskDetails.setObjectId(objectId);
		}
		if (objectTypeMaster != null) {
			taskDetails.setObjectName(objectTypeMaster.getObjectName());
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceIdNo))) {
			taskDetails.setObjectInstanceIdNo(objectInstanceIdNo);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignToName))) {
			taskDetails.setAssignToName(assignToName);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceUuid))) {
			taskDetails.setObjectInstanceUuid(objectInstanceUuid);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipStatusId))) {
			taskDetails.setWipStatusId(wipStatusId);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipStatusName))) {
			taskDetails.setWipStatusName(wipStatusName);
		}
		
		taskDetails.setStatus("Active");
		taskDetails.setCreatedById(Long.valueOf("5501"));
		taskDetails.setCreatedByName("ANDROKTASIAI");
		taskDetails.setCreatedDate(LocalDate.now());
		
		taskDetailsRepository.save(taskDetails);
		
		outCome.setData(taskDetailsMapper.toDto(taskDetails));
		outCome.setMessage("Data Inserted Successfully");
		outCome.setOutcome(true);
		
		return outCome;
	}

}
