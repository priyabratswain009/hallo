package com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.PrimaryResubmissionServiceLinesMasterRepository;

public interface PrimaryResubmissionServiceLinesMasterRepo extends PrimaryResubmissionServiceLinesMasterRepository{
    @Query(value="From PrimaryResubmissionServiceLinesMaster")
    List<PrimaryResubmissionServiceLinesMaster> getAllServiceLineDetails();

	@Query(value="From PrimaryResubmissionServiceLinesMaster WHERE changeHealthPrimaryResubmisionMasterId = :changeHealthPrimaryResubmisionMasterId")
	List<PrimaryResubmissionServiceLinesMaster> getPrimarySubmissionServiceDetails(@Param("changeHealthPrimaryResubmisionMasterId") Long changeHealthPrimaryResubmisionMasterId);

    @Query("FROM PrimaryResubmissionServiceLinesMaster WHERE changeHealthPrimaryResubmisionMasterId = :changeHealthPrimaryResubmisionMasterId")
    List<PrimaryResubmissionServiceLinesMaster> getServiceLineMasterDataOnClaimSubmission(@Param("changeHealthPrimaryResubmisionMasterId") Long changeHealthPrimaryResubmisionMasterId);
}
