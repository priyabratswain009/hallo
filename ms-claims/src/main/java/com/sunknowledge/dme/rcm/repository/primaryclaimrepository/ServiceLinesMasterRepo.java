package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ServiceLinesMaster;
import com.sunknowledge.dme.rcm.repository.ServiceLinesMasterRepository;

public interface ServiceLinesMasterRepo extends ServiceLinesMasterRepository{
    @Query(value="From ServiceLinesMaster")
    List<ServiceLinesMaster> getAllServiceLineDetails();

	@Query(value="From ServiceLinesMaster WHERE changeHealthPrimarySubmisionMasterId = :primarySubmisionMasterId")
	List<ServiceLinesMaster> getPrimarySubmissionServiceDetails(@Param("primarySubmisionMasterId") Long primarySubmisionMasterId);

    @Query("FROM ServiceLinesMaster WHERE changeHealthPrimarySubmisionMasterId = :changeHealthPrimarySubmisionMasterId")
    List<ServiceLinesMaster> getServiceLineMasterDataOnClaimSubmission(@Param("changeHealthPrimarySubmisionMasterId") Long changeHealthPrimarySubmisionMasterId);
}
