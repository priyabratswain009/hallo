package com.sunknowledge.dme.rcm.repository.wip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.repository.WipStatusMasterRepository;

public interface WipStatusMasterRepo extends WipStatusMasterRepository {

	@Query(value = "From WipStatusMaster where wipStatusId=:wipStatusId")
	WipStatusMaster getWipStatusMasterBywipStatusId(@Param("wipStatusId") Long wipStatusId);

	@Query(value = "From WipStatusMaster where taskId=:taskId and wipStatusName=:wipStatusName")
	WipStatusMaster getWipStatusMasterBytaskIdandwipStatusName(@Param("taskId") Long taskId,
			@Param("wipStatusName") String wipStatusName);

	@Query(value = "From WipStatusMaster where wipStatusName=:wipStatusName")
	WipStatusMaster getWipStatusMasterByName(@Param("wipStatusName") String wipStatusName);

	@Query(value = "From WipStatusMaster where status=:status")
	List<WipStatusMaster> getAllWIPStatusMasterByStatus(@Param("status") String status);

	@Query(value = "From WipStatusMaster where taskId=:taskId")
	List<WipStatusMaster> getAllWIPStatusMasterBytaskId(@Param("taskId") Long taskId);

	@Query(value = "From WipStatusMaster where taskName=:taskName")
	List<WipStatusMaster> getAllWIPStatusMasterBytaskName(@Param("taskName") String taskName);

	@Query(value = "From  WipStatusMaster where taskId in (select taskId from TaskTypeMaster where objectId=:objectId)")
	List<WipStatusMaster> getAllWIPStatusMasterByObjectId(@Param("objectId") Long objectId);

	@Query(value = "From  WipStatusMaster where taskId in (select taskId from TaskTypeMaster where objectName=:objectName)")
	List<WipStatusMaster> getAllWIPStatusMasterByObjectName(@Param("objectName") String objectName);

}
