package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster;

import reactor.core.publisher.Mono;

public interface MemberElligibilityMasterRepositoryExtended extends MemberElligibilityMasterRepository {

	@Query(value="select * from t_member_elligibility_master where sales_order_id=:salesOrderId")
	Mono<MemberElligibilityMaster> getmemberElligibilityData(@Param("salesOrderId") Long salesOrderId);

	@Query(value="CALL so.so_primary_elligibility_datafetch(:soid)")
	Mono<MemberElligibilityMaster> insertMemberPrimaryElligibilityData(@Param("soid") Long soid);

	@Query(value="CALL so.so_secondary_elligibility_datafetch(:soid)")
	Mono<MemberElligibilityMaster> insertMemberSecondaryElligibilityData(@Param("soid") Long soid);
	
}
