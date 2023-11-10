package com.sunknowledge.dme.rcm.repository.wip;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.WipQueueDetails;
import com.sunknowledge.dme.rcm.repository.WipQueueDetailsRepository;

public interface WIPQueueDetailsRepo extends WipQueueDetailsRepository {

	@Query(value = "From WipQueueDetails where objectInstanceIdUuid=:objectInstanceIdUuid")
	WipQueueDetails getWIPQueueDetailsByWIPUUID(@Param("objectInstanceIdUuid") UUID objectInstanceIdUuid);

	@Query(value = "From WipQueueDetails where wipStatusId=:wipStatusId")
	WipQueueDetails getWIPQueueDetailsByWIPStatusId(@Param("wipStatusId") String wipStatusId);

	@Query(value = "From WipQueueDetails where wipQueueDetailsId=:wipQueueDetailsId")
	WipQueueDetails getWIPQueueDetailsById(@Param("wipQueueDetailsId") Long wipQueueDetailsId);

	@Query(value = "From WipQueueDetails where taskId=:taskId and wipQueueDetailsId not in(:wipQueueDetailsId)")
	List<WipQueueDetails> getWIPQueueDetailsForTaskIdstateUpdate(@Param("wipQueueDetailsId") Long wipQueueDetailsId,
			@Param("taskId") Long taskId);

	@Query(value = "From WipQueueDetails where taskId=:taskId")
	List<WipQueueDetails> getWIPQueueDetailsByTaskId(@Param("taskId") Long taskId);

	@Query(value = "From WipQueueDetails where taskName=:taskName")
	List<WipQueueDetails> getWIPQueueDetailsBytaskName(@Param("taskName") String taskName);

	@Query(value = "From WipQueueDetails where objectId=:objectId")
	List<WipQueueDetails> getWIPQueueDetailsByObjectId(@Param("objectId") Long objectId);

	@Query(value = "From WipQueueDetails where objectInstanceId=:objectInstanceId")
	List<WipQueueDetails> getWIPQueueDetailsByObjectInstanceId(@Param("objectInstanceId") Long objectInstanceId);

	@Query(value = "From WipQueueDetails where assignedToId=:assignedToId")
	List<WipQueueDetails> getWIPQueueDetailsByAssignedToId(@Param("assignedToId") Long assignedToId);

	@Query(value = "From WipQueueDetails where objectInstanceId=:objectInstanceId and taskName=:taskName order by wipQueueDetailsId desc")
	WipQueueDetails getWIPQueueDetailsBytaskandObject(@Param("objectInstanceId") Long objectInstanceId,
			@Param("taskName") String taskName);

	@Query(value = "From WipQueueDetails where taskId=:taskId and objectInstanceId=:objectInstanceId and objectId=:objectId and state!='Completed'")
	WipQueueDetails updateWIPStateRecords(@Param("taskId") Long taskId,
			@Param("objectInstanceId") Long objectInstanceId, @Param("objectId") Long objectId);

	@Query(value = "From WipQueueDetails where objectInstanceId=:objectInstanceId and taskName=:taskName and wipStatusName=:wipStatusName")
	WipQueueDetails validateTaskDetailsEntry(@Param("objectInstanceId") Long objectInstanceId,
			@Param("taskName") String taskName, @Param("wipStatusName") String wipStatusName);

	@Query(value = "From WipQueueDetails where state=:state")
	List<WipQueueDetails> getWIPQueueDetailsBystate(@Param("state") String state);

	@Query(value = "From WipQueueDetails where state=:state and taskName=:taskName and objectId=:objectId")
	List<WipQueueDetails> getWIPQueueDetailsByWIPStateandObject(@Param("state") String state,
			@Param("taskName") String taskName, @Param("objectId") Long objectId);

	@Query(value = "From WipQueueDetails where state=:state and taskId=:taskId and objectId=:objectId and assignedDate=:assignedDate")
	List<WipQueueDetails> getWIPQueueDetailsByWIPStateObjectIdAssignedDate(@Param("state") String state,
			@Param("taskId") Long taskId, @Param("objectId") Long objectId,
			@Param("assignedDate") LocalDate assignedDate);

	@Query(value = "From WipQueueDetails where objectInstanceId=:objectInstanceId and objectId=:objectId and state=:state")
	List<WipQueueDetails> getWIPQueueDetailsByWIPObjectInstanceIdandObjectId(
			@Param("objectInstanceId") Long objectInstanceId, @Param("objectId") Long objectId);

	@Query(value = "From WipQueueDetails where objectInstanceId=:objectInstanceId and objectId=:objectId and state=:state")
	List<WipQueueDetails> getWIPQueueDetailsByWIPObjectInstanceIdandObjectIdstate(
			@Param("objectInstanceId") Long objectInstanceId, @Param("objectId") Long objectId,
			@Param("state") String state);

	@Query(value = "From WipQueueDetails where assignedToId=:assignedToId and state=:state")
	List<WipQueueDetails> getWIPQueueDetailsByWIPAssignedToIdIdandState(@Param("assignedToId") Long assignedToId,
			@Param("state") String state);

	@Query(value = "From WipQueueDetails where assignedDate=:assignedDate")
	List<WipQueueDetails> getWIPQueueDetailsByWIPassignedDate(@Param("assignedDate") LocalDate assignedDate);

	@Query(value = "From WipQueueDetails where createdDate=:createdDate")
	List<WipQueueDetails> getWIPQueueDetailsByWIPcreatedDate(@Param("createdDate") LocalDate createdDate);

	@Query(value = "select max(wipQueueDetailsId) From WipQueueDetails where objectInstanceId=:objectInstanceId and taskName=:taskName")
	Long getmaxWIPQueueDetails(@Param("objectInstanceId") Long objectInstanceId, @Param("taskName") String taskName);

	@Query(value = "From WipQueueDetails where wipStatusId=:wipStatusId and objectId=:objectId and state=:state and assignedToId=:assignedToId and assignedDate=:assignedDate")
	List<WipQueueDetails> getWIPQueueDetailsByWIPStateObjectIdasssngIdAssignedDate(
			@Param("wipStatusId") String wipStatusId, @Param("objectId") Long objectId, @Param("state") String state,
			@Param("assignedToId") Long assignedToId, @Param("assignedDate") LocalDate assignedDate);

	@Query(value = "From WipQueueDetails where assignedDate=:assignedDate and wipStatusId=:wipStatusId and assignedToId=:assignedToId and assignedById=:assignedById")
	List<WipQueueDetails> getWIPQueueDetailsByWIPasssngIdAssignedDate(@Param("assignedDate") LocalDate assignedDate,
			@Param("wipStatusId") String wipStatusId, @Param("assignedToId") Long assignedToId,
			@Param("assignedById") Long assignedById);

	@Query(value = "From WipQueueDetails where wipStatusId=:wipStatusId and state=:state")
	List<WipQueueDetails> getWIPQueueDetailsForReandAssignment(@Param("wipStatusId") String wipStatusId,
			@Param("state") String state);

	@Query(value = "From WipQueueDetails where wipStatusId=:wipStatusId and state=:state and wipQueueDetailsUuid=:wipQueueDetailsUuid")
	WipQueueDetails getWIPQueueDetailsForwipstateandinstanceUUID(@Param("wipStatusId") String wipStatusId,
			@Param("state") String state, @Param("state") UUID wipQueueDetailsUuid);

	@Query(value = "From WipQueueDetails where objectInstanceIdUuid=:objectInstanceIdUuid and state=:state and objectId=:objectId")
	List<WipQueueDetails> getWIPQueueDetailsForObjectUUIDstateObjectId(
			@Param("objectInstanceIdUuid") UUID objectInstanceIdUuid, @Param("state") String state,
			@Param("objectId") Long objectId);

	@Query(value = "From WipQueueDetails where targetedDate < :targetedDate and state != 'Completed'")
	List<WipQueueDetails> getWIPQueueDetailsByTargetedDate(@Param("targetedDate") LocalDate targetedDate);

}
