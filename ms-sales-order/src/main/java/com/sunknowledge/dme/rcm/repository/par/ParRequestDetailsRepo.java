package com.sunknowledge.dme.rcm.repository.par;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.repository.ParRequestDetailsRepository;

import reactor.core.publisher.Mono;

public interface ParRequestDetailsRepo extends ParRequestDetailsRepository {
	
	@Query("select * from t_par_request_details where par_id=:parId and par_request_type=:requestType")
	Mono<ParRequestDetails> getParRequestDetailsForInit(@Param("parId") Long parId, @Param("requestType") String requestType);
	
	@Query("SELECT * FROM so.t_par_request_details\r\n"
			+ "ORDER BY par_request_details_id DESC limit 1")
	Mono<ParRequestDetails> getLatestParRequestDetailIdForRenew();
	
	@Query("select * from t_par_request_details where par_id=:parId and par_request_type=:requestType and par_no is null")
	Mono<ParRequestDetails> getParRequestDetailsForRenewal(@Param("parId") Long parId, @Param("requestType") String requestType);

	@Query("select * from t_par_request_details where par_id=:parId and doc_qr_code=:docQrCode")
	Mono<ParRequestDetails> getParRequestDetailsWithDocQrCode(@Param("parId") Long parId, @Param("docQrCode") String docQrCode);
}
