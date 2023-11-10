package com.sunknowledge.dme.rcm.repository.wip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.repository.ObjectTypeMasterRepository;

public interface ObjectTypeMasterRepo extends ObjectTypeMasterRepository {

	@Query(value="From ObjectTypeMaster where objectId=:objectId")
	ObjectTypeMaster getObjectTypeMasterByobjectId(@Param("objectId") Long objectId);

	@Query(value="From ObjectTypeMaster where objectName=:objectName")
	ObjectTypeMaster getObjectTypeMasterByName(@Param("objectName") String objectName);

	@Query(value="From ObjectTypeMaster where status=:status")
	List<ObjectTypeMaster> getObjectTypeMasterByStatus(@Param("status") String status);
	
}
