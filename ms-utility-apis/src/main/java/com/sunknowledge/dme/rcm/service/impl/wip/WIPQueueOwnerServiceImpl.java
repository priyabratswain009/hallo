package com.sunknowledge.dme.rcm.service.impl.wip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.repository.wip.TaskTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.WIPQueueDetailsRepo;
import com.sunknowledge.dme.rcm.repository.wip.WIPQueueOwnerRepo;
import com.sunknowledge.dme.rcm.repository.wip.WipStatusMasterRepo;
import com.sunknowledge.dme.rcm.service.wip.WIPQueueOwnerService;

@Service
public class WIPQueueOwnerServiceImpl implements WIPQueueOwnerService {

	@Autowired
	TaskTypeMasterRepo taskTypeMasterRepository;
	@Autowired
	WipStatusMasterRepo wipStatusMasterRepository;
	@Autowired
	WIPQueueOwnerRepo objWIPQueueOwnerRepository;
	@Autowired
	WIPQueueDetailsRepo WIPQueueDetailsRepository;

	@Override
	public ServiceOutcome<WipQueueOwner> saveWIPQueueOwner(String WIPStatusName, String TaskName, String assignedById,
			String assignedByName, String assignedToId, String assignedtoName) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueOwner> outCome = new ServiceOutcome<WipQueueOwner>();
		WipQueueOwner objWipQueueOwner = new WipQueueOwner();
		ServiceOutcome<WipQueueOwner> obj = new ServiceOutcome<WipQueueOwner>();

		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByName(TaskName);
		WipStatusMaster objWipStatusMaster = wipStatusMasterRepository.getWipStatusMasterByName(WIPStatusName);

		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objWipStatusMaster.getWipStatusId()))) {
			objWipQueueOwner.setWipStatusId(String.valueOf(objWipStatusMaster.getWipStatusId()));
		}
		if (CommonUtilities.isStringNullOrBlank(WIPStatusName)) {
			objWipQueueOwner.setWipStatusName(WIPStatusName);
		}
		if (CommonUtilities.isStringNullOrBlank(String.valueOf(objTaskTypeMaster.getTaskId()))) {
			objWipQueueOwner.setTaskId(objTaskTypeMaster.getTaskId());
		}
		if (CommonUtilities.isStringNullOrBlank(TaskName)) {
			objWipQueueOwner.setTaskName(TaskName);
		}
		if (CommonUtilities.isStringNullOrBlank(assignedById)) {
			objWipQueueOwner.setAssignedById(Long.valueOf(assignedById));
		}
		if (CommonUtilities.isStringNullOrBlank(assignedByName)) {
			objWipQueueOwner.setAssignedByName(assignedByName);
		}
		if (CommonUtilities.isStringNullOrBlank(assignedToId)) {
			objWipQueueOwner.setAssignedToId(Long.valueOf(assignedToId));
		}
		if (CommonUtilities.isStringNullOrBlank(assignedtoName)) {
			objWipQueueOwner.setAssignedToName(assignedtoName);
		}
		objWipQueueOwner.setAssignedDate(LocalDate.now());

		objWipQueueOwner.setCreatedById(Long.valueOf("5501"));
		objWipQueueOwner.setCreatedByName("System_I");
		objWipQueueOwner.setCreatedDate(LocalDate.now());

		obj.setData(objWipQueueOwner);
		obj.setMessage("Data Inserted Successfully");
		obj.setOutcome(true);

		objWIPQueueOwnerRepository.save(objWipQueueOwner);

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueOwner> updateWIPQueueOwner(Long wipQueueOwnerId, String WIPStatusId,
			String WIPStatusName, Long taskId, String TaskName, String assignedById, String assignedByName,
			String assignedToId, String assignedtoName, String assigneddate) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueOwner> outCome = new ServiceOutcome<WipQueueOwner>();

		WipQueueOwner objWipQueueOwner = objWIPQueueOwnerRepository.getWipQueueOwnerBywipQueueOwnerId(wipQueueOwnerId);

		if (objWipQueueOwner == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found to Update");
			outCome.setOutcome(false);
		} else {

			objWipQueueOwner.setStatus("Inactive");
			objWIPQueueOwnerRepository.save(objWipQueueOwner);

			if (CommonUtilities.isStringNullOrBlank(WIPStatusId)) {
				objWipQueueOwner.setWipStatusId(WIPStatusId);
			}
			if (CommonUtilities.isStringNullOrBlank(WIPStatusName)) {
				objWipQueueOwner.setWipStatusName(WIPStatusName);
			}
			if (CommonUtilities.isStringNullOrBlank(String.valueOf(taskId))) {
				objWipQueueOwner.setTaskId(taskId);
			}
			if (CommonUtilities.isStringNullOrBlank(TaskName)) {
				objWipQueueOwner.setTaskName(TaskName);
			}
			if (CommonUtilities.isStringNullOrBlank(assignedById)) {
				objWipQueueOwner.setAssignedById(Long.valueOf(assignedById));
			}
			if (CommonUtilities.isStringNullOrBlank(assignedByName)) {
				objWipQueueOwner.setAssignedByName(assignedByName);
			}
			if (CommonUtilities.isStringNullOrBlank(assignedToId)) {
				objWipQueueOwner.setAssignedToId(Long.valueOf(assignedToId));
			}
			if (CommonUtilities.isStringNullOrBlank(assignedtoName)) {
				objWipQueueOwner.setAssignedToName(assignedtoName);
			}
			if (CommonUtilities.isStringNullOrBlank(assigneddate)) {
				objWipQueueOwner.setAssignedDate(CommonUtilities.stringtodateConverter(assigneddate));
			}
			objWipQueueOwner.setUpdatedById(Long.valueOf("5501"));
			objWipQueueOwner.setUpdatedByName("System_U");
			objWipQueueOwner.setUpdatedDate(LocalDate.now());

			objWipQueueOwner.setStatus("Active");
			objWIPQueueOwnerRepository.save(objWipQueueOwner);

			outCome.setData(objWipQueueOwner);
			outCome.setMessage("Data Updated Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerById(Long wipQueueOwnerId) {
		// TODO Auto-generated method stub
		ServiceOutcome<WipQueueOwner> outCome = new ServiceOutcome<WipQueueOwner>();
		WipQueueOwner objWipQueueOwner = new WipQueueOwner();
		objWipQueueOwner = objWIPQueueOwnerRepository.getWipQueueOwnerBywipQueueOwnerId(wipQueueOwnerId);
		if (objWipQueueOwner == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objWipQueueOwner);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<WipQueueOwner> getWIPQueueOwnerByWIPStatusId(String wipStatusId) {

		ServiceOutcome<WipQueueOwner> outCome = new ServiceOutcome<WipQueueOwner>();
		WipQueueOwner objWipQueueOwner = new WipQueueOwner();
		objWipQueueOwner = objWIPQueueOwnerRepository.getWipQueueOwnerBywipStatusId(wipStatusId);
		if (objWipQueueOwner == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objWipQueueOwner);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByWIPTaskId(Long wipTaskId) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWipQueueOwnerBywipTaskId(wipTaskId);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByTaskName(String wipTaskId) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWipQueueOwnerBywipTaskName(wipTaskId);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedToId(Long assignedToId) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWIPQueueOwnerByAssignedToId(assignedToId);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getAllWIPQueueOwner() {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.findAll();
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByStatus(String status) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWipQueueOwnerBystatus(status);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByAssignedById(Long queueOwner) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWIPQueueOwnerByAssignedById(queueOwner);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByQueueOwnerName(String queueOwnerName) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWIPQueueOwnerByQueueOwnerName(queueOwnerName);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

	@Override
	public ServiceOutcome<List<WipQueueOwner>> getWIPQueueOwnerByObjectId(Long objectId) {
		// TODO Auto-generated method stub
		List<WipQueueOwner> objList = new ArrayList<>();
		ServiceOutcome<List<WipQueueOwner>> outCome = new ServiceOutcome<List<WipQueueOwner>>();

		objList = objWIPQueueOwnerRepository.getWIPQueueOwnerByObjectId(objectId);
		if (objList.size() == 0) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved");
			outCome.setOutcome(true);
		}
		return outCome;
	}

}
