package com.sunknowledge.dme.rcm.service.impl.wip;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.repository.wip.ObjectTypeMasterRepo;
import com.sunknowledge.dme.rcm.repository.wip.TaskTypeMasterRepo;
import com.sunknowledge.dme.rcm.service.wip.TaskTypeMasterService;

@Service
public class TaskTypeMasterServiceImplementation implements TaskTypeMasterService {

	@Autowired
	ObjectTypeMasterRepo objectTypeMasterRepository;
	@Autowired
	TaskTypeMasterRepo taskTypeMasterRepository;

	@Override
	public ServiceOutcome<TaskTypeMaster> saveTaskTypeMaster(String taskName, String objectName) {
		// TODO Auto-generated method stub
		TaskTypeMaster objTaskTypeMaster = new TaskTypeMaster();
		ServiceOutcome<TaskTypeMaster> obj = new ServiceOutcome<TaskTypeMaster>();
		ObjectTypeMaster objObjectTypeMaster = objectTypeMasterRepository.getObjectTypeMasterByName(objectName);

		objTaskTypeMaster.setTaskName(taskName);
		objTaskTypeMaster.setObjectId(objObjectTypeMaster.getObjectId());
		objTaskTypeMaster.setObjectName(objectName);
		objTaskTypeMaster.setStatus("Active");
		objTaskTypeMaster.setCreatedById(Long.valueOf("5501"));
		objTaskTypeMaster.setCreatedByName("ANDROKTASIAI");
		objTaskTypeMaster.setCreatedDate(LocalDate.now());

		obj.setData(objTaskTypeMaster);
		obj.setMessage("Data Inserted Successfully");
		obj.setOutcome(true);

		taskTypeMasterRepository.save(objTaskTypeMaster);

		return obj;
	}

	@Override
	public ServiceOutcome<TaskTypeMaster> updateTaskTypeMaster(String taskName, String objectName) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskTypeMaster> outcome = new ServiceOutcome<TaskTypeMaster>();
		ObjectTypeMaster obj = objectTypeMasterRepository.getObjectTypeMasterByName(objectName);

		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByName(taskName);

		if (objTaskTypeMaster == null) {
			outcome.setData(null);
			outcome.setMessage("No Data Found");
			outcome.setOutcome(false);
		} else {

			objTaskTypeMaster.setStatus("Inactive");
			taskTypeMasterRepository.save(objTaskTypeMaster);

			objTaskTypeMaster.setStatus("Active");
			objTaskTypeMaster.setTaskName(taskName);
			objTaskTypeMaster.setObjectId(obj.getObjectId());
			objTaskTypeMaster.setObjectName(objectName);
			objTaskTypeMaster.setUpdatedById(Long.valueOf("5501"));
			objTaskTypeMaster.setUpdatedByName("System_U");
			objTaskTypeMaster.setUpdatedDate(LocalDate.now());
			taskTypeMasterRepository.save(objTaskTypeMaster);

			outcome.setData(objTaskTypeMaster);
			outcome.setMessage("Data Updated Successfully");
			outcome.setOutcome(true);
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterById(Long objectId) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskTypeMaster> obj = new ServiceOutcome<TaskTypeMaster>();
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByTaskId(objectId);

		if (objTaskTypeMaster == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objTaskTypeMaster);
			obj.setMessage("Data Returned Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<TaskTypeMaster> getTaskTypeMasterByName(String taskName) {
		// TODO Auto-generated method stub
		ServiceOutcome<TaskTypeMaster> obj = new ServiceOutcome<TaskTypeMaster>();
		TaskTypeMaster objTaskTypeMaster = taskTypeMasterRepository.getTaskTypeMasterByName(taskName);

		if (objTaskTypeMaster == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objTaskTypeMaster);
			obj.setMessage("Data Returned Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<List<TaskTypeMaster>> getAllTaskTypeMaster() {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskTypeMaster>> obj = new ServiceOutcome<List<TaskTypeMaster>>();
		List<TaskTypeMaster> objList = taskTypeMasterRepository.findAll();

		if (objList == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objList);
			obj.setMessage("Data Returned Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<List<TaskTypeMaster>> getTaskTypeMasterByStatus(String status) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<TaskTypeMaster>> outCome = new ServiceOutcome<List<TaskTypeMaster>>();
		List<TaskTypeMaster> objList = taskTypeMasterRepository.getTaskTypeMasterByStatus(status);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Returned Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

}
