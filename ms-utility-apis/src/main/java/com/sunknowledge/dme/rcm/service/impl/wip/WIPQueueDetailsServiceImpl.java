package com.sunknowledge.dme.rcm.service.impl.wip;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.dto.wip.WIPStateandObject;
import com.sunknowledge.dme.rcm.repository.wip.ObjectTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.TaskTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.WIPQueueDetailsRepo;
import com.sunknowledge.dme.rcm.repository.wip.WipStatusMasterRepo;
import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.WipQueueDetailsMapper;
import com.sunknowledge.dme.rcm.service.wip.WIPQueueDetailsService;
import com.sunknowledge.dme.rcm.service.wip.dto.WipQueueDetailscustomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WIPQueueDetailsServiceImpl implements WIPQueueDetailsService {

	@Autowired
	WIPQueueDetailsRepo wIPQueueDetailsRepository;
	@Autowired
	TaskTypeMasterRepo taskTypeMasterRepository;
	@Autowired
	ObjectTypeMasterRepo objectTypeMasterRepository;
	@Autowired
	WipStatusMasterRepo wipStatusMasterRepository;
	@Autowired
	WipQueueDetailsMapper wipQueueDetailsMapper;

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> saveObjectTypeMaster(Long wipStatusId, Long taskId, Long objectId,
			Long objectInstanceId, Long wipSetById, String wipSetByName, Long assignedById, String assignedByName,
			Long assignedToId, String assignedtoName, String assignedDate, String assignedStatus, String wipNotes,
			String assignmentNotes, String assignmentStatusNotes, String objectInstanceIdNo) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();
		WipQueueDetails objWipQueueDetails = new WipQueueDetails();
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByTaskId(taskId);
		Long val = wIPQueueDetailsRepository.getmaxWIPQueueDetails(objectInstanceId, objTaskTypeMaster.getTaskName());
		ObjectTypeMaster objObjectTypeMaster = objectTypeMasterRepository.getObjectTypeMasterByobjectId(objectId);
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterBywipStatusId(wipStatusId);
		WipQueueDetails objWipQueueDetailsvldt = wIPQueueDetailsRepository.validateTaskDetailsEntry(objectInstanceId,
				objTaskTypeMaster.getTaskName(), objWipStatusMaster.getWipStatusName());
		WipQueueDetails updateWIPStateRecords = wIPQueueDetailsRepository.updateWIPStateRecords(taskId,
				objectInstanceId, objectId);
		WipQueueDetails objWipQueueDetailsupdt = wIPQueueDetailsRepository.getWIPQueueDetailsById(val);

		if (updateWIPStateRecords != null) {
			updateWIPStateRecords.setState("Completed");
			wIPQueueDetailsRepository.save(updateWIPStateRecords);
		}

		if (objWipQueueDetailsvldt != null) {

			if (objWipQueueDetailsvldt.getState().equalsIgnoreCase("Ongoing")) {
				outCome.setData(null);
				outCome.setMessage("Task is in Ongoing state, Kindly complete.");
				outCome.setOutcome(true);
			} else {
				outCome.setData(null);
				outCome.setMessage("Task is Completed, Kindly add an updated task");
				outCome.setOutcome(true);
			}
		} else {

			if (objWipQueueDetailsupdt != null) {
				objWipQueueDetailsupdt.setStatus("Inactive");
				wIPQueueDetailsRepository.save(objWipQueueDetailsupdt);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipStatusId))) {
				objWipQueueDetails.setWipStatusId(String.valueOf(wipStatusId));
			}

			if (CommonUtilities.isStringNullOrBlank(objWipStatusMaster.getWipStatusName())) {
				objWipQueueDetails.setWipStatusName(objWipStatusMaster.getWipStatusName());
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
				objWipQueueDetails.setTaskId(taskId);
			}

			if (CommonUtilities.isStringNullOrBlank(objTaskTypeMaster.getTaskName())) {
				objWipQueueDetails.setTaskName(objTaskTypeMaster.getTaskName());
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectId))) {
				objWipQueueDetails.setObjectId(objectId);
			}

			if (CommonUtilities.isStringNullOrBlank(objObjectTypeMaster.getObjectName())) {
				objWipQueueDetails.setObjectName(objObjectTypeMaster.getObjectName());
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceId))) {
				objWipQueueDetails.setObjectInstanceId(objectInstanceId);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipSetById))) {
				objWipQueueDetails.setWipSetById(wipSetById);
			}

			if (CommonUtilities.isStringNullOrBlank(wipSetByName)) {
				objWipQueueDetails.setWipSetByName(wipSetByName);
			}

			objWipQueueDetails.setWipSetDateTime(LocalDate.now());

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedById))) {
				objWipQueueDetails.setAssignedById(assignedById);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedByName)) {
				objWipQueueDetails.setAssignedByName(assignedByName);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedToId))) {
				objWipQueueDetails.setAssignedToId(assignedToId);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedtoName)) {
				objWipQueueDetails.setAssignedToName(assignedtoName);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedDate)) {
				objWipQueueDetails.setAssignedDate(CommonUtilities.stringtodateConverter(assignedDate));
			}

			if (CommonUtilities.isStringNullOrBlank(assignedStatus)) {
				objWipQueueDetails.setAssignedStatus(assignedStatus);
			}

			if (CommonUtilities.isStringNullOrBlank(wipNotes)) {
				objWipQueueDetails.setWipNotes(wipNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(assignmentNotes)) {
				objWipQueueDetails.assignmentNotes(assignmentNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(assignmentStatusNotes)) {
				objWipQueueDetails.setAssignmentStatusNotes(assignmentStatusNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(objectInstanceIdNo)) {
				objWipQueueDetails.setObjectInstanceIdNo(objectInstanceIdNo);
			}

			objWipQueueDetails.setState("Ongoing");
			objWipQueueDetails.setStatus("Active");
			objWipQueueDetails.setCreatedById(Long.valueOf("5501"));
			objWipQueueDetails.setCreatedByName("Arijit");
			objWipQueueDetails.setCreatedDate(LocalDate.now());

			wIPQueueDetailsRepository.save(objWipQueueDetails);

			if (objWipQueueDetailsupdt != null) {
				objWipQueueDetailsupdt.setState("Completed");
				objWipQueueDetailsupdt.setStatus("Active");
				wIPQueueDetailsRepository.save(objWipQueueDetailsupdt);
			}

			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetailsupdt));
			outCome.setMessage("Data Inserted Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> updateObjectTypeMaster(Long wipQueueDetailsId, Long wipStatusId,
			Long taskId, Long objectInstanceId, Long wipSetById, String wipSetByName, String wipSetDateTime,
			Long assignedById, String assignedByName, Long assignedToId, String assignedtoName, String assignedDate,
			String assignedStatus, String wipNotes, String assignmentNotes, String assignmentStatusNotes,
			String objectInstanceIdNo) {
		// TODO Auto-generated method stub

		WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsById(wipQueueDetailsId);
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByTaskId(taskId);
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterBywipStatusId(wipStatusId);
		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();

		if (objWipQueueDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found to Update");
			outCome.setOutcome(false);
		} else {
			objWipQueueDetails.setStatus("Inactive");
			wIPQueueDetailsRepository.save(objWipQueueDetails);

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipStatusId))) {
				objWipQueueDetails.setWipStatusId(String.valueOf(wipStatusId));
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
				objWipQueueDetails.setTaskId(taskId);
			}

			if (CommonUtilities.isStringNullOrBlank(objTaskTypeMaster.getTaskName())) {
				objWipQueueDetails.setTaskName(objTaskTypeMaster.getTaskName());
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(objectInstanceId))) {
				objWipQueueDetails.setObjectInstanceId(objectInstanceId);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(wipSetById))) {
				objWipQueueDetails.setWipSetById(wipSetById);
			}

			if (CommonUtilities.isStringNullOrBlank(wipSetByName)) {
				objWipQueueDetails.setWipSetByName(wipSetByName);
			}

			if (CommonUtilities.isStringNullOrBlank(wipSetDateTime)) {
				objWipQueueDetails.setWipSetDateTime(CommonUtilities.stringtodateConverter(wipSetDateTime));
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedById))) {
				objWipQueueDetails.setAssignedById(assignedById);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedByName)) {
				objWipQueueDetails.setAssignedByName(assignedByName);
			}

			if (CommonUtilities.isStringNullOrBlank(String.valueOf(assignedToId))) {
				objWipQueueDetails.setAssignedToId(assignedToId);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedtoName)) {
				objWipQueueDetails.setAssignedToName(assignedtoName);
			}

			if (CommonUtilities.isStringNullOrBlank(assignedDate)) {
				objWipQueueDetails.setAssignedDate(CommonUtilities.stringtodateConverter(assignedDate));
			}

			if (CommonUtilities.isStringNullOrBlank(assignedStatus)) {
				objWipQueueDetails.setAssignedStatus(assignedStatus);
			}

			if (CommonUtilities.isStringNullOrBlank(wipNotes)) {
				objWipQueueDetails.setWipNotes(wipNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(assignmentNotes)) {
				objWipQueueDetails.assignmentNotes(assignmentNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(assignmentStatusNotes)) {
				objWipQueueDetails.setAssignmentStatusNotes(assignmentStatusNotes);
			}

			if (CommonUtilities.isStringNullOrBlank(objectInstanceIdNo)) {
				objWipQueueDetails.setObjectInstanceIdNo(objectInstanceIdNo);
			}

			objWipQueueDetails.setStatus("Active");
			objWipQueueDetails.setUpdatedById(Long.valueOf("5501"));
			objWipQueueDetails.setUpdatedDate(LocalDate.now());

			wIPQueueDetailsRepository.save(objWipQueueDetails);

			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Update Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> updateWIPDetailsState(Long wipQueueDetailsId, String state) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();
		int flag = 0;
		WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsById(wipQueueDetailsId);
		List<WipQueueDetails> listWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsForTaskIdstateUpdate(wipQueueDetailsId, objWipQueueDetails.getTaskId());

		for (WipQueueDetails obj : listWipQueueDetails) {
			if (!obj.getState().equalsIgnoreCase("completed")) {
				outCome.setMessage("Kindly update the state of previous WIPQueueDetail " + obj.getWipQueueDetailsId());
				outCome.setData(wipQueueDetailsMapper.toDto(obj));
				outCome.setOutcome(false);
				flag++;
			}
		}

		if (flag == 0) {
			objWipQueueDetails.setState(state);
			wIPQueueDetailsRepository.save(objWipQueueDetails);

			outCome.setMessage("State updated successfully");
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> assignWIP(Long wipQueueDetailsId, Long assignedById,
			String assignedByName, Long assignedToId, String assignedtoName, String assignedDate, String assignedStatus,
			String wipNotes, String assignmentNotes, String assignmentStatusNotes) {
		// TODO Auto-generated method stub

		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();
		WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsById(wipQueueDetailsId);

		if (objWipQueueDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found to Update");
			outCome.setOutcome(false);
		} else {
			objWipQueueDetails.setAssignedById(assignedById);
			objWipQueueDetails.setAssignedByName(assignedByName);
			objWipQueueDetails.setAssignedToId(assignedToId);
			objWipQueueDetails.setAssignedToName(assignedtoName);
			objWipQueueDetails.setAssignedDate(CommonUtilities.stringtodateConverter(assignedDate));
			objWipQueueDetails.setAssignedStatus(assignedStatus);
			objWipQueueDetails.setWipNotes(wipNotes);
			objWipQueueDetails.setAssignmentNotes(assignmentNotes);
			objWipQueueDetails.setAssignmentStatusNotes(assignmentStatusNotes);

			wIPQueueDetailsRepository.save(objWipQueueDetails);

			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("WIP assigned Successfully");
			outCome.setOutcome(true);
		}

		return null;
	}

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByUUId(UUID wipQueueDetailsUUID) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();

		System.out.println("Inside Service");
		WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsByWIPUUID(wipQueueDetailsUUID);

		if (objWipQueueDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueDetailsDTO> getWIPQueueDetailsByWIPStatusId(Long WIPStatusId) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueDetailsDTO> outCome = new ServiceOutcome<WipQueueDetailsDTO>();

		WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPStatusId(String.valueOf(WIPStatusId));

		wipQueueDetailsMapper.toDto(objWipQueueDetails);

		if (objWipQueueDetails == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskId(Long taskId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsByTaskId(taskId);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPTaskName(String taskName) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsBytaskName(taskName);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectId(Long objectId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsByObjectId(objectId);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByObjectInstanceId(Long objectInstanceId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByObjectInstanceId(objectInstanceId);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToId(Long assignedToId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByAssignedToId(assignedToId);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPState(String state) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsBystate(state);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateandObject(String state, String taskName,
			Long Object) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WIPStateandObject>> outCome = new ServiceOutcome<List<WIPStateandObject>>();
		List<WIPStateandObject> objList = new ArrayList<>();

		List<WipQueueDetails> listWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPStateandObject(state, taskName, Object);

		if (listWipQueueDetails.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			for (WipQueueDetails obj : listWipQueueDetails) {

				WIPStateandObject objWIPStateandObject = new WIPStateandObject();

				String diff = CommonUtilities.dateDifference(obj.getAssignedDate(), LocalDate.now());

				if (CommonUtilities.isStringNullOrBlank(diff)) {
					objWIPStateandObject.setDuration(diff);
				}
				if (obj.getObjectId() != null) {
					objWIPStateandObject.setObjectId(obj.getObjectId());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getObjectName())) {
					objWIPStateandObject.setObjectName(obj.getObjectName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getTaskName())) {
					objWIPStateandObject.setTaskName(obj.getTaskName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getWipStatusName())) {
					objWIPStateandObject.setWipStatusName(obj.getWipStatusName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getWipStatusId())) {
					objWIPStateandObject.setWipStatusId(obj.getWipStatusId());
				}
				if (obj.getAssignedToId() != null) {
					objWIPStateandObject.setAssignedToId(obj.getAssignedToId());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getAssignedToName())) {
					objWIPStateandObject.setAssignedToName(obj.getAssignedToName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getState())) {
					objWIPStateandObject.setState(obj.getState());
				}
				objList.add(objWIPStateandObject);
			}
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(
			Long ObjectInstanceId, Long Object) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(ObjectInstanceId, Object);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByAssignedToIdandState(Long assignedToId,
			String state) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPAssignedToIdIdandState(assignedToId, state);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdandState(
			Long ObjectInstanceId, Long Object, String state) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdstate(ObjectInstanceId, Object, state);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WIPStateandObject>> getWIPQueueDetailsByWIPStateObjectIdAssignedDate(String state,
			Long taskId, Long Object, String assignedDate) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WIPStateandObject>> outCome = new ServiceOutcome<List<WIPStateandObject>>();
		List<WIPStateandObject> objList = new ArrayList<>();

		List<WipQueueDetails> listWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPStateObjectIdAssignedDate(state, taskId, Object,
						CommonUtilities.stringtodateConverter(assignedDate));

		if (listWipQueueDetails.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			for (WipQueueDetails obj : listWipQueueDetails) {

				WIPStateandObject objWIPStateandObject = new WIPStateandObject();

				String diff = CommonUtilities.dateDifference(obj.getAssignedDate(), LocalDate.now());

				if (CommonUtilities.isStringNullOrBlank(diff)) {
					objWIPStateandObject.setDuration(diff);
				}
				if (obj.getObjectId() != null) {
					objWIPStateandObject.setObjectId(obj.getObjectId());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getObjectName())) {
					objWIPStateandObject.setObjectName(obj.getObjectName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getTaskName())) {
					objWIPStateandObject.setTaskName(obj.getTaskName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getWipStatusName())) {
					objWIPStateandObject.setWipStatusName(obj.getWipStatusName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getWipStatusId())) {
					objWIPStateandObject.setWipStatusId(obj.getWipStatusId());
				}
				if (obj.getAssignedToId() != null) {
					objWIPStateandObject.setAssignedToId(obj.getAssignedToId());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getAssignedToName())) {
					objWIPStateandObject.setAssignedToName(obj.getAssignedToName());
				}
				if (CommonUtilities.isStringNullOrBlank(obj.getState())) {
					objWIPStateandObject.setState(obj.getState());
				}
				objList.add(objWIPStateandObject);
			}
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWICreatedAssignedDate(String createdDate,
			String assignedDate) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();
		List<WipQueueDetails> objWipQueueDetails = new ArrayList<>();

		if (CommonUtilities.isStringNullOrBlank(assignedDate)) {
			objWipQueueDetails = wIPQueueDetailsRepository
					.getWIPQueueDetailsByWIPassignedDate(CommonUtilities.stringtodateConverter(assignedDate));
		} else if (CommonUtilities.isStringNullOrBlank(createdDate)) {
			objWipQueueDetails = wIPQueueDetailsRepository
					.getWIPQueueDetailsByWIPcreatedDate(CommonUtilities.stringtodateConverter(createdDate));
		}

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(
			Long objectId, String wipstatId, String state, Long assignedtoId, String assignedtoDate) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(wipstatId, objectId, state, assignedtoId,
						CommonUtilities.stringtodateConverter(assignedtoDate));

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByWIPasssngIdAssignedDate(String AssignedDate,
			String wipstatId, Long assignedtoId, Long assignedById) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsByWIPasssngIdAssignedDate(CommonUtilities.stringtodateConverter(AssignedDate),
						wipstatId, assignedtoId, assignedById);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReandAssignment(String wipstatusId,
			String date, String state) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsForReandAssignment(wipstatusId, state);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForReassignment(Long assignedToId,
			String assignedToName, UUID wipQueueDetailsUuid, String date, String state, String assignmentNotes) {
		// TODO Auto-generated method stub

		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();
		List<WipQueueDetails> objList = new ArrayList<>();
		List<WipQueueDetailsDTO> respoutCome = getWIPQueueDetailsByWIPState(state).getData();

		for (WipQueueDetailsDTO obj : respoutCome) {

			if (obj.getWipQueueDetailsUuid().toString().equalsIgnoreCase(wipQueueDetailsUuid.toString())) {

				WipQueueDetails objWipQueueDetails = wIPQueueDetailsRepository
						.getWIPQueueDetailsByWIPUUID(wipQueueDetailsUuid);

				objWipQueueDetails.setStatus("Inactive");
				wIPQueueDetailsRepository.save(objWipQueueDetails);

				objWipQueueDetails.setAssignedToId(assignedToId);
				objWipQueueDetails.setAssignedToName(assignedToName);
				objWipQueueDetails.setAssignedDate(LocalDate.now());
				objWipQueueDetails.setUpdatedById(Long.valueOf("5501"));
				objWipQueueDetails.setUpdatedByName("Aj");
				objWipQueueDetails.setUpdatedDate(LocalDate.now());
				objWipQueueDetails.setAssignedById(Long.valueOf("1097"));
				objWipQueueDetails.setAssignedByName("Mirzafr");
				objWipQueueDetails.setAssignmentNotes(assignmentNotes);
				objWipQueueDetails.setStatus("Active");
				wIPQueueDetailsRepository.save(objWipQueueDetails);

				objList.add(objWipQueueDetails);
			}
		}

		if (objList == null || objList.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(objList));
			outCome.setMessage("Data Assigned Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsForAssignment(String wipStatusIdtoAssign,
			Long assignedToId, UUID objectInstanceIdUuid, String objectInstanceIdNo, String assignedToName,
			String wipstatusId, String date, String state, String assignmentNotes)
			throws ParseException, org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
		System.out.println("Inside the method");
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();
		List<WipQueueDetails> list = new ArrayList<>();

		WipStatusMaster towipStatusMaster = wipStatusMasterRepository
				.getWipStatusMasterBywipStatusId(Long.valueOf(wipStatusIdtoAssign));
		TaskTypeMaster taskTypeMaster = taskTypeMasterRepository
				.getTaskTypeMasterByTaskId(towipStatusMaster.getTaskId());
		List<WipQueueDetailsDTO> objWipQueueDetails = getWIPQueueDetailsForReandAssignment(wipstatusId, date, state)
				.getData();

		System.out.println("List of Data: " + objWipQueueDetails.size());

		for (WipQueueDetailsDTO obj : objWipQueueDetails) {
			ServiceOutcome<LocalDate> finalDate = null;
			ServiceOutcome<LocalDate> updatedDate = null;
			if (obj.getUpdatedDate() == null) {
				System.out.println("Updated Date is null");
				finalDate = CommonUtilities.dateCompare(obj.getCreatedDate(),
						CommonUtilities.stringtodateConverter(date));
				System.out.println(
						"finalDate  ======" + finalDate.getData() + "====Created Date====" + obj.getCreatedDate());
			} else {
				System.out.println("Updated Date is NOT null");
				updatedDate = CommonUtilities.dateCompare(obj.getCreatedDate(), obj.getUpdatedDate());
				finalDate = CommonUtilities.dateCompare(updatedDate.getData(),
						CommonUtilities.stringtodateConverter(date));
			}

			if (finalDate.getMessage().equalsIgnoreCase("Equal")) {
				System.out.println("Dates are equal");
				WipQueueDetails wipQueueDetails = new WipQueueDetails();

				wipQueueDetails.setWipStatusId(wipStatusIdtoAssign);
				wipQueueDetails.setWipStatusName(towipStatusMaster.getWipStatusName());
				wipQueueDetails.setTaskId(towipStatusMaster.getTaskId());
				wipQueueDetails.setTaskName(towipStatusMaster.getTaskName());
				wipQueueDetails.setObjectId(taskTypeMaster.getObjectId());
				wipQueueDetails.setObjectName(taskTypeMaster.getObjectName());
				wipQueueDetails.setObjectInstanceIdUuid(objectInstanceIdUuid);
				wipQueueDetails.setObjectInstanceIdNo(objectInstanceIdNo);
				wipQueueDetails.setStatus("Active");
				wipQueueDetails.setState("Ongoing");
				wipQueueDetails.setAssignedStatus("Assigned");
				wipQueueDetails.setAssignedById(assignedToId);
				wipQueueDetails.setAssignedByName(assignedToName);
				wipQueueDetails.setAssignedDate(LocalDate.now());
				wipQueueDetails.setAssignmentNotes(assignmentNotes);
				wipQueueDetails.setAssignedToId(assignedToId);
				wipQueueDetails.setAssignedToName(assignedToName);
				wipQueueDetails.setWipSetById(assignedToId);
				wipQueueDetails.setWipSetByName(assignedToName);
				wipQueueDetails.setWipSetDateTime(LocalDate.now());
				wipQueueDetails.setCreatedById(Long.valueOf("5501"));
				wipQueueDetails.setCreatedByName("Arijit");
				wipQueueDetails.setCreatedDate(LocalDate.now());

				wIPQueueDetailsRepository.save(wipQueueDetails);

				list.add(wipQueueDetails);
			}
		}
		if (list.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(list));
			outCome.setMessage("Data Inserted Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailscustomDTO>> getWIPQueueDetailsForObjectUUIDstateObjectId(
			UUID objectInstanceIdUuid, String state, Long objectId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailscustomDTO>> outCome = new ServiceOutcome<List<WipQueueDetailscustomDTO>>();
		List<WipQueueDetailscustomDTO> returndata = new ArrayList<>();

		List<WipQueueDetails> objWipQueueDetails = wIPQueueDetailsRepository
				.getWIPQueueDetailsForObjectUUIDstateObjectId(objectInstanceIdUuid, state, objectId);

		if (objWipQueueDetails == null || objWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			WipQueueDetailscustomDTO wipQueueDetailscustomDTO = new WipQueueDetailscustomDTO();
			for (WipQueueDetails wipQueueDetails : objWipQueueDetails) {
				wipQueueDetailscustomDTO = setData(wipQueueDetails);
				returndata.add(wipQueueDetailscustomDTO);
			}

			outCome.setData(returndata);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	public static WipQueueDetailscustomDTO setData(WipQueueDetails wipQueueDetails) {

		WipQueueDetailscustomDTO WipQueueDetailscustomDTO = new WipQueueDetailscustomDTO();

		WipQueueDetailscustomDTO.setAssignedById(wipQueueDetails.getAssignedById());
		WipQueueDetailscustomDTO.setAssignedByName(wipQueueDetails.getAssignedByName());
		WipQueueDetailscustomDTO.setAssignedDate(wipQueueDetails.getAssignedDate());
		WipQueueDetailscustomDTO.setAssignedToId(wipQueueDetails.getAssignedToId());
		WipQueueDetailscustomDTO.setAssignedToName(wipQueueDetails.getAssignedToName());
		WipQueueDetailscustomDTO.setAssignmentNotes(wipQueueDetails.getAssignmentNotes());
		WipQueueDetailscustomDTO.setAssignmentStatusNotes(wipQueueDetails.getAssignmentStatusNotes());
		WipQueueDetailscustomDTO.setWipNotes(wipQueueDetails.getWipNotes());
		WipQueueDetailscustomDTO
				.setDuration(CommonUtilities.dateDifference(wipQueueDetails.getAssignedDate(), LocalDate.now()));

		return WipQueueDetailscustomDTO;
	}

	@Override
	public ServiceOutcome<List<WipQueueDetailsDTO>> getWIPQueueDetailsByTargetedDate(String TargettedDate) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipQueueDetailsDTO>> outCome = new ServiceOutcome<List<WipQueueDetailsDTO>>();
		List<WipQueueDetails> listWipQueueDetails = wIPQueueDetailsRepository.getWIPQueueDetailsByTargetedDate(CommonUtilities.stringtodateConverter(TargettedDate));
		
		if (listWipQueueDetails == null || listWipQueueDetails.isEmpty()) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(wipQueueDetailsMapper.toDto(listWipQueueDetails));
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}
		
		return outCome;
	}

}
