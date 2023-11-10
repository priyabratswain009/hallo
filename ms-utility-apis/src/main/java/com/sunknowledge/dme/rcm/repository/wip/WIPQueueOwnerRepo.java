package com.sunknowledge.dme.rcm.repository.wip;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.WipQueueOwner;
import com.sunknowledge.dme.rcm.repository.WipQueueOwnerRepository;

public interface WIPQueueOwnerRepo extends WipQueueOwnerRepository{

	@Query(value="From WipQueueOwner where wipQueueOwnerId=(select max(wipQueueOwnerId) from WipQueueOwner)")
	WipQueueOwner getLastInsertedQueueOwner();

	@Query(value="From WipQueueOwner where wipQueueOwnerId=:wipQueueOwnerId")
	WipQueueOwner getWipQueueOwnerBywipQueueOwnerId(@Param("wipQueueOwnerId") Long wipQueueOwnerId);

	@Query(value="From WipQueueOwner where wipStatusId=:wipStatusId")
	WipQueueOwner getWipQueueOwnerBywipStatusId(@Param("wipStatusId") String wipStatusId);
	
	@Query(value="From WipQueueOwner where taskId=:taskId")
	List<WipQueueOwner> getWipQueueOwnerBywipTaskId(@Param("taskId") Long taskId);
	
	@Query(value="From WipQueueOwner where taskName=:taskName")
	List<WipQueueOwner> getWipQueueOwnerBywipTaskName(@Param("taskName") String taskName);
	
	@Query(value="From WipQueueOwner where status=:status")
	List<WipQueueOwner> getWipQueueOwnerBystatus(@Param("status") String status);
	
	@Query(value="From WipQueueOwner where assignedToId=:assignedToId")
	List<WipQueueOwner> getWIPQueueOwnerByAssignedToId(@Param("assignedToId") Long assignedToId);
	
	@Query(value="From WipQueueOwner where assignedById=:assignedById")
	List<WipQueueOwner> getWIPQueueOwnerByAssignedById(@Param("assignedById") Long assignedById);
	
	@Query(value="From WipQueueOwner where assignedByName=:assignedByName")
	List<WipQueueOwner> getWIPQueueOwnerByQueueOwnerName(@Param("assignedByName") String assignedByName);
	
	@Query(value="From WipQueueOwner where taskId in (select taskId from TaskTypeMaster where objectId=:objectId)")
	List<WipQueueOwner> getWIPQueueOwnerByObjectId(@Param("objectId") Long objectId);
	
}
