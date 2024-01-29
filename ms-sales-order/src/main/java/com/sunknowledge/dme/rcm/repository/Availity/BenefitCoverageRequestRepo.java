package com.sunknowledge.dme.rcm.repository.Availity;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageRequestRepository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface BenefitCoverageRequestRepo extends BenefitCoverageRequestRepository {

	@Query(value = "select MAX(benefit_coverage_request_id) from t_benefit_coverage_request;")
    Mono<String> getlastentry();

	@Query(value = "From BenefitCoverageRequest where memberId=:memberId")
	BenefitCoverageRequest coverageinfo(@Param("memberId") String memberId);
}
