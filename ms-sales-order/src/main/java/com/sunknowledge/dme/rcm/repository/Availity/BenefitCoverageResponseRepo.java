package com.sunknowledge.dme.rcm.repository.Availity;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.BenefitCoverageResponseRepository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.DoubleStream;

public interface BenefitCoverageResponseRepo extends BenefitCoverageResponseRepository {

	@Query(value="From BenefitCoverageResponse where benefitCoverageRequestId=:benefitCoverageRequestId")
	BenefitCoverageResponse getcoverageDetails(@Param("benefitCoverageRequestId") String benefitCoverageRequestId);

	@Query(value="From BenefitCoverageResponse where memberId=:memberId ORDER BY benefitCoverageResponseId desc")
	List<BenefitCoverageResponse> getcoverageDetailswithmemberID(@Param("memberId") String memberId);

    @Query(value="select * From t_benefit_coverage_response bcr where bcr.subscriber_member_id=:memberId")
    Flux<BenefitCoverageResponse> findAllByMemberId(@Param("memberId") String memberId);
}
