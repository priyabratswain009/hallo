package com.sunknowledge.dme.rcm.service.impl.wip;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.repository.wip.ObjectTypeMasterRepo;
import com.sunknowledge.dme.rcm.service.wip.ObjectTypeMasterService;

@Service
public class ObjectTypeMasterServiceImplementation implements ObjectTypeMasterService {

	@Autowired
	ObjectTypeMasterRepo ObjectTypeMasterRepository;

	@Override
	public ServiceOutcome<ObjectTypeMaster> saveObjectTypeMaster(String objectName) {
		// TODO Auto-generated method stub
		ServiceOutcome<ObjectTypeMaster> obj = new ServiceOutcome<ObjectTypeMaster>();
		ObjectTypeMaster objObjectTypeMasterDTO = new ObjectTypeMaster();
		objObjectTypeMasterDTO.setObjectName(objectName);
		objObjectTypeMasterDTO.setCreatedById(Long.valueOf("5501"));
		objObjectTypeMasterDTO.setCreatedByName("System_I");
		objObjectTypeMasterDTO.setCreatedDate(LocalDate.now());

		obj.setData(objObjectTypeMasterDTO);
		obj.setMessage("Data Inserted Successfully");
		obj.setOutcome(true);

		ObjectTypeMasterRepository.save(objObjectTypeMasterDTO);

		return obj;
	}

	@Override
	public ServiceOutcome<ObjectTypeMaster> updateObjectTypeMaster(String objectName) {
		// TODO Auto-generated method stub
		ServiceOutcome<ObjectTypeMaster> outcome = new ServiceOutcome<ObjectTypeMaster>();
		ObjectTypeMaster obj = ObjectTypeMasterRepository.getObjectTypeMasterByName(objectName);

		if (obj == null) {
			outcome.setData(null);
			outcome.setMessage("No Data Found");
			outcome.setOutcome(false);
		} else {
			obj.setStatus("Inactive");
			ObjectTypeMasterRepository.save(obj);

			obj.setStatus("Active");
			obj.setObjectName(objectName);
			obj.setUpdatedById(Long.valueOf("5501"));
			obj.setUpdatedByName("System_U");
			obj.setUpdatedDate(LocalDate.now());

			outcome.setData(obj);
			outcome.setMessage("Data Updated Successfully");
			outcome.setOutcome(true);

			ObjectTypeMasterRepository.save(obj);
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterById(Long objectId) {
		// TODO Auto-generated method stub
		ServiceOutcome<ObjectTypeMaster> outCome = new ServiceOutcome<ObjectTypeMaster>();
		ObjectTypeMaster obj = ObjectTypeMasterRepository.getObjectTypeMasterByobjectId(Long.valueOf(objectId));

		if (obj == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(obj);
			outCome.setMessage("Data Recieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<ObjectTypeMaster> getObjectTypeMasterByName(String objectName) {
		// TODO Auto-generated method stub
		ServiceOutcome<ObjectTypeMaster> outCome = new ServiceOutcome<ObjectTypeMaster>();
		ObjectTypeMaster obj = ObjectTypeMasterRepository.getObjectTypeMasterByName(objectName);

		if (obj == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(obj);
			outCome.setMessage("Data Recieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

	@Override
	public ServiceOutcome<List<ObjectTypeMaster>> getAllObjectTypeMasterByName() {
		// TODO Auto-generated method stub
		ServiceOutcome<List<ObjectTypeMaster>> obj = new ServiceOutcome<List<ObjectTypeMaster>>();
		List<ObjectTypeMaster> objList = ObjectTypeMasterRepository.findAll();

		if (objList == null) {
			obj.setData(null);
			obj.setMessage("No Data Found");
			obj.setOutcome(false);
		} else {
			obj.setData(objList);
			obj.setMessage("Data Recieved Successfully");
			obj.setOutcome(true);
		}

		return obj;
	}

	@Override
	public ServiceOutcome<List<ObjectTypeMaster>> getObjectTypeMasterByStatus(String status) {
		// TODO Auto-generated method stub
		ServiceOutcome<List<ObjectTypeMaster>> outCome = new ServiceOutcome<List<ObjectTypeMaster>>();
		List<ObjectTypeMaster> objList = ObjectTypeMasterRepository.getObjectTypeMasterByStatus(status);

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("No Data Found");
			outCome.setOutcome(false);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Recieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

}
