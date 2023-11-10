package com.sunknowledge.dme.rcm.service.impl.wip;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.repository.wip.ObjectTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.TaskTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.WIPQueueDetailsRepo;
import com.sunknowledge.dme.rcm.repository.wip.WIPQueueOwnerRepo;
import com.sunknowledge.dme.rcm.repository.wip.WipStatusMasterRepo;
import com.sunknowledge.dme.rcm.service.wip.WIPStatusMasterService;

@Service
public class WIPStatusMasterServiceImplementation implements WIPStatusMasterService {

	@Autowired
	WipStatusMasterRepo wipStatusMasterRepository;
	@Autowired
	TaskTypeMasterRepo taskTypeMasterRepository;
	@Autowired
	WIPQueueOwnerRepo wipQueueOwnerRepository;
	@Autowired
	WIPQueueDetailsRepo wipQueueDetailsRepository;
	@Autowired
	ObjectTypeMasterRepo objectTypeMasterRepository;

	@Override
	public ServiceOutcome<WipStatusMaster> saveWIPStatusMaster(String taskName, String statusName) {
		// TODO Auto-generated method stub
		WipStatusMaster objWipStatusMaster = new WipStatusMaster();
		ServiceOutcome<WipStatusMaster> obj = new ServiceOutcome<WipStatusMaster>();
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByName(taskName);

		System.out.println("Task Master ======== " + objTaskTypeMaster.getTaskId());

		objWipStatusMaster.setTaskId(objTaskTypeMaster.getTaskId());
		objWipStatusMaster.setTaskName(taskName);
		objWipStatusMaster.setWipStatusName(statusName);

		objWipStatusMaster.setStatus("Active");
		objWipStatusMaster.setCreatedById(Long.valueOf("5501"));
		objWipStatusMaster.setCreatedByName("System_I");
		objWipStatusMaster.setCreatedDate(LocalDate.now());

		obj.setData(objWipStatusMaster);
		obj.setMessage("Data Inserted Successfully");
		obj.setOutcome(true);

		wipStatusMasterRepository.save(objWipStatusMaster);

		return obj;
	}

	@Override
	public ServiceOutcome<WipStatusMaster> updateWIPStatusMaster(String statusName, String taskName) {
		// TODO Auto-generated method stub
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByName(taskName);
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterByName(statusName);
		ServiceOutcome<WipStatusMaster> obj = new ServiceOutcome<WipStatusMaster>();

		if (objWipStatusMaster == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {

			objWipStatusMaster.setStatus("Inactive");
			wipStatusMasterRepository.save(objWipStatusMaster);

			objWipStatusMaster.setStatus("Active");
			objWipStatusMaster.setTaskName(taskName);
			objWipStatusMaster.setTaskId(objTaskTypeMaster.getTaskId());
			objWipStatusMaster.setWipStatusName(statusName);
			objWipStatusMaster.setUpdatedById(Long.valueOf("5501"));
			objWipStatusMaster.setUpdatedByName("System_U");
			objWipStatusMaster.setUpdatedDate(LocalDate.now());
			wipStatusMasterRepository.save(objWipStatusMaster);

			obj.setData(objWipStatusMaster);
			obj.setMessage("Data Updated Successfully");
			obj.setOutcome(true);

		}

		return obj;
	}

	@Override
	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterById(Long wipStatusId) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipStatusMaster> obj = new ServiceOutcome<WipStatusMaster>();
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterBywipStatusId(wipStatusId);

		if (objWipStatusMaster == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objWipStatusMaster);
			obj.setMessage("Data Retieved Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<WipStatusMaster> getWIPStatusMasterByName(String taskName) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipStatusMaster> obj = new ServiceOutcome<WipStatusMaster>();
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterByName(taskName);

		if (objWipStatusMaster == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objWipStatusMaster);
			obj.setMessage("Data Retieved Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMaster() {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> obj = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.findAll();

		if (objList == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objList);
			obj.setMessage("Data Retieved Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByStatus(String status) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> outCome = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.getAllWIPStatusMasterByStatus(status);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskId(Long taskId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> outCome = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.getAllWIPStatusMasterBytaskId(taskId);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByTaskName(String taskName) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> outCome = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.getAllWIPStatusMasterBytaskName(taskName);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectId(Long objectId) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> outCome = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.getAllWIPStatusMasterByObjectId(objectId);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipStatusMaster>> getAllWIPStatusMasterByObjectName(String objectName) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<WipStatusMaster>> outCome = new ServiceOutcome<List<WipStatusMaster>>();
		List<WipStatusMaster> objList = wipStatusMasterRepository.getAllWIPStatusMasterByObjectName(objectName);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipStatusMaster> createWIPStatusMasterByObjectInstanceId(Long ObjectInstanceId,
			UUID ObjectInstanceUUID, Long ObjectId, Long taskId, String wipStatusName, Long assignedToId,
			String assignedToName) {
		// TODO Auto-generated method stub

		ServiceOutcome<WipStatusMaster> outCome = new ServiceOutcome<WipStatusMaster>();
		TaskTypeMaster taskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByTaskId(taskId);
		ObjectTypeMaster objectTypeMaster = objectTypeMasterRepository.getObjectTypeMasterByobjectId(ObjectId);

		WipStatusMaster wipStatusMaster = new WipStatusMaster();
		WipQueueOwner wipQueueOwner = new WipQueueOwner();
		WipQueueDetails wipQueueDetails = new WipQueueDetails();

		wipStatusMaster.setWipStatusName(wipStatusName);
		wipStatusMaster.setTaskId(taskId);
		wipStatusMaster.setTaskName(taskTypeMaster.getTaskName());
		wipStatusMaster.setStatus("Active");
		wipStatusMaster.setCreatedById(Long.valueOf("5501"));
		wipStatusMaster.setCreatedByName("ANDROKTASIAI");
		wipStatusMaster.setCreatedDate(LocalDate.now());

		wipStatusMasterRepository.save(wipStatusMaster);

		WipStatusMaster objwipStatusMaster = wipStatusMasterRepository
				.getWipStatusMasterBytaskIdandwipStatusName(taskId, wipStatusName);

		wipQueueOwner.setWipStatusId(String.valueOf(objwipStatusMaster.getWipStatusId()));
		wipQueueOwner.setWipStatusName(wipStatusName);
		wipQueueOwner.setTaskId(taskId);
		wipQueueOwner.setTaskName(taskTypeMaster.getTaskName());
		wipQueueOwner.setAssignedById(Long.valueOf("5501"));
		wipQueueOwner.setAssignedByName("ANDROKTASIAI");
		wipQueueOwner.setAssignedToId(assignedToId);
		wipQueueOwner.setAssignedToName(assignedToName);
		wipQueueOwner.setAssignedDate(LocalDate.now());
		wipQueueOwner.setStatus("Active");
		wipQueueOwner.setCreatedById(Long.valueOf("5501"));
		wipQueueOwner.setCreatedByName("ANDROKTASIAI");
		wipQueueOwner.setCreatedDate(LocalDate.now());

		wipQueueOwnerRepository.save(wipQueueOwner);

		WipQueueOwner objWipQueueOwner = wipQueueOwnerRepository.getLastInsertedQueueOwner();

		wipQueueDetails.setWipStatusId(String.valueOf(objwipStatusMaster.getWipStatusId()));
		wipQueueDetails.setWipStatusName(wipStatusName);
		wipQueueDetails.setTaskId(taskId);
		wipQueueDetails.setTaskName(taskTypeMaster.getTaskName());
		wipQueueDetails.setObjectId(ObjectId);
		wipQueueDetails.setObjectName(objectTypeMaster.getObjectName());
		wipQueueDetails.setObjectInstanceId(ObjectInstanceId);
		wipQueueDetails.setWipSetById(objwipStatusMaster.getCreatedById());
		wipQueueDetails.setWipSetByName(objwipStatusMaster.getCreatedByName());
		wipQueueDetails.setWipSetDateTime(objwipStatusMaster.getCreatedDate());
		wipQueueDetails.setAssignedById(objWipQueueOwner.getAssignedById());
		wipQueueDetails.setAssignedByName(objWipQueueOwner.getAssignedByName());
		wipQueueDetails.setAssignedToId(assignedToId);
		wipQueueDetails.setAssignedToName(assignedToName);
		wipQueueDetails.setAssignedDate(objWipQueueOwner.getAssignedDate());
		wipQueueDetails.setAssignedStatus("assigned");
		wipQueueDetails.setStatus("Active");
		wipQueueDetails.setCreatedById(Long.valueOf("5501"));
		wipQueueDetails.setCreatedByName("ANDROKTASIAI");
		wipQueueDetails.setCreatedDate(LocalDate.now());
		wipQueueDetails.setObjectInstanceIdUuid(objectTypeMaster.getObjectTypeMasterUuid());

		wipQueueDetailsRepository.save(wipQueueDetails);

		outCome.setData(wipStatusMaster);
		outCome.setMessage("Data Inserted Successfully");
		outCome.setOutcome(true);

		return outCome;
	}

	@Override
	public ServiceOutcome<WipStatusMaster> createWIPStatusandWipDetailsasperUI(Long objectInstanceId, String objectName,
			Long taskId, String wipStatusName, Long assignedToId, String assignedToName, String targetedDate) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipStatusMaster> outCome = new ServiceOutcome<WipStatusMaster>();
		ObjectTypeMaster ObjectTypeMaster = objectTypeMasterRepository.getObjectTypeMasterByName(objectName);
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByTaskId(taskId);

		WipStatusMaster wipStatusMaster = new WipStatusMaster();
		WipQueueOwner wipQueueOwner = new WipQueueOwner();
		WipQueueDetails wipQueueDetails = new WipQueueDetails();
		
		//update state of previous task to Completed
		WipQueueDetails updateWIPStateRecords = wipQueueDetailsRepository.updateWIPStateRecords(taskId, objectInstanceId, ObjectTypeMaster.getObjectId());
		if(updateWIPStateRecords!=null) {
			updateWIPStateRecords.setState("Completed");
			wipQueueDetailsRepository.save(updateWIPStateRecords);
		}

		wipStatusMaster.setWipStatusName(wipStatusName);
		wipStatusMaster.setTaskId(taskId);
		wipStatusMaster.setTaskName(objTaskTypeMaster.getTaskName());

		wipStatusMaster.setStatus("Active");
		wipStatusMaster.setCreatedById(Long.valueOf("5501"));
		wipStatusMaster.setCreatedByName("ANDROKTASIAI");

		wipStatusMasterRepository.save(wipStatusMaster);

		WipStatusMaster objwipStatusMaster = wipStatusMasterRepository
				.getWipStatusMasterBytaskIdandwipStatusName(taskId, wipStatusName);

		wipQueueOwner.setWipStatusId(String.valueOf(objwipStatusMaster.getWipStatusId()));
		wipQueueOwner.setWipStatusName(objwipStatusMaster.getWipStatusName());
		wipQueueOwner.setTaskId(taskId);
		wipQueueOwner.setTaskName(objTaskTypeMaster.getTaskName());
		wipQueueOwner.setAssignedById(objwipStatusMaster.getCreatedById());
		wipQueueOwner.setAssignedByName(objwipStatusMaster.getCreatedByName());
		wipQueueOwner.setAssignedToId(assignedToId);
		wipQueueOwner.setAssignedToName(assignedToName);
		wipQueueOwner.setAssignedDate(objwipStatusMaster.getCreatedDate());

		wipQueueOwner.setStatus("Active");
		wipQueueOwner.setCreatedById(Long.valueOf("5501"));
		wipQueueOwner.setCreatedByName("ANDROKTASIAI");

		wipQueueOwnerRepository.save(wipQueueOwner);

		wipQueueDetails.setWipStatusId(String.valueOf(objwipStatusMaster.getWipStatusId()));
		wipQueueDetails.setWipStatusName(objwipStatusMaster.getWipStatusName());
		wipQueueDetails.setTaskId(objTaskTypeMaster.getTaskId());
		wipQueueDetails.setTaskName(objTaskTypeMaster.getTaskName());
		wipQueueDetails.setObjectId(ObjectTypeMaster.getObjectId());
		wipQueueDetails.setObjectName(ObjectTypeMaster.getObjectName());
		wipQueueDetails.setObjectInstanceId(objectInstanceId);
		wipQueueDetails.wipSetById(objwipStatusMaster.getCreatedById());
		wipQueueDetails.setWipSetByName(objwipStatusMaster.getCreatedByName());
		wipQueueDetails.setWipSetDateTime(objwipStatusMaster.getCreatedDate());
		wipQueueDetails.setAssignedById(objwipStatusMaster.getCreatedById());
		wipQueueDetails.setAssignedByName(objwipStatusMaster.getCreatedByName());
		wipQueueDetails.setAssignedToId(assignedToId);
		wipQueueDetails.setAssignedToName(assignedToName);
		wipQueueDetails.setAssignedDate(objwipStatusMaster.getCreatedDate());
		wipQueueDetails.setTargetedDate(CommonUtilities.stringtodateConverter(targetedDate));
		wipQueueDetails.setAssignedStatus("Assigned");
		wipQueueDetails.setState("Ongoing");
		
		wipQueueDetails.setStatus("Active");
		wipQueueDetails.setCreatedById(Long.valueOf("5501"));
		wipQueueDetails.setCreatedByName("ANDROKTASIAI");
		
		wipQueueDetailsRepository.save(wipQueueDetails);
		
		outCome.setData(objwipStatusMaster);
		outCome.setMessage("Data Inserted Successfully");
		outCome.setOutcome(true);

		return outCome;
	}

}
