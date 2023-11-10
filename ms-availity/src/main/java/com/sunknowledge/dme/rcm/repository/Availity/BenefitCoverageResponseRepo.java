package com.sunknowledge.dme.rcm.repository.Availity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;

public interface BenefitCoverageResponseRepo extends BenefitCoverageResponseRepository {

	@Query(value="From BenefitCoverageResponse where benefitCoverageRequestId=:benefitCoverageRequestId")
	BenefitCoverageResponse getcoverageDetails(@Param("benefitCoverageRequestId") String benefitCoverageRequestId);
	
	@Query(value="From BenefitCoverageResponse where memberId=:memberId ORDER BY benefitCoverageResponseId desc")
	List<BenefitCoverageResponse> getcoverageDetailswithmemberID(@Param("memberId") String memberId);
}
