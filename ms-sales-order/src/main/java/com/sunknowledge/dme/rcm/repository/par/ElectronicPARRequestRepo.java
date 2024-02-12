package com.sunknowledge.dme.rcm.repository.par;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.repository.EparRequestRepository;

import reactor.core.publisher.Mono;

public interface ElectronicPARRequestRepo extends EparRequestRepository {
	
    @Query("select * from t_epar_request tpm where tpm.so_id = :soId")
    Mono<EparRequest> getEpaRequestonSoId(@Param("soId") Long soId);

}
