package com.sunknowledge.dme.rcm.repository.par;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.repository.ParSoMapRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ParSoMapRepo extends ParSoMapRepository {
    @Query("select * from t_par_so_map tpsm where tpsm.so_id = :salesOrderId and tpsm.par_id = :parId")
    Mono<ParSoMap> getParSOMapOnSalesOrderNpar(@Param("salesOrderId") Long salesOrderId, @Param("parId") Long parId);

    @Query("select tpsm.* from t_par_master tpm, t_par_so_map tpsm where tpm.par_id = tpsm.par_id \n" +
        "and tpm.patient_id = :patientId and tpm.status = 'Active' and tpsm.status = 'Active' and tpm.par_status = :parStatus")
    Mono<ParSoMap> getParMasterOnPatientnHcpcsCode(@Param("patientId") Long patientId, @Param("parStatus") String parStatus);

    @Query("select * from t_par_so_map tpsm where tpsm.par_id = :parId")
    Flux<ParSoMap> getParSOMapOnpar(@Param("parId") Long parId);
}
