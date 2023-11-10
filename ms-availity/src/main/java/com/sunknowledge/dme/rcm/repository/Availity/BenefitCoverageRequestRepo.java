package com.sunknowledge.dme.rcm.repository.Availity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;

public interface BenefitCoverageRequestRepo extends BenefitCoverageRequestRepository {

	@Query(value = "select MAX(benefitCoverageRequestId) from BenefitCoverageRequest")
	String getlastentry();

	@Query(value = "From BenefitCoverageRequest where memberId=:memberId")
	BenefitCoverageRequest coverageinfo(@Param("memberId") String memberId);       
}
