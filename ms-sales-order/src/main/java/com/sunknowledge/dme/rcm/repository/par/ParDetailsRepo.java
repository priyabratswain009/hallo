package com.sunknowledge.dme.rcm.repository.par;

import com.sunknowledge.dme.rcm.domain.ParDetails;
import com.sunknowledge.dme.rcm.repository.ParDetailsRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface ParDetailsRepo extends ParDetailsRepository {
    @Query("select * from t_par_details tpd where tpd.par_id = :parId and tpd.hcpcs_code = :hcpcsCode")
    Mono<ParDetails> getParDetailsOnParNitem(@Param("parId") Long parId, @Param("hcpcsCode") String hcpcsCode);

    @Query("select tpd.* from t_par_master tpm, t_par_details tpd, t_par_so_map tpsm \n" +
        "where tpm.par_id = tpd.par_id and tpd.par_id = tpsm.par_id and tpsm.so_id = :salesorderId and tpd.item_id = :itemId and tpm.par_id = :parId")
    Mono<ParDetails> getParDetailsOnSalesorderNitem(@Param("salesorderId") Long salesorderId, @Param("itemId") Long itemId, @Param("parId") Long parId);

    @Query("select * from t_par_details tpd where tpd.par_id = :parId and tpd.hcpcs_code = :hcpcsCode and tpd.item_id = :itemId")
    Mono<ParDetails> getDelinkItem(@Param("parId") Long parId, @Param("hcpcsCode") String hcpcsCode, @Param("itemId") Long itemId);

    @Query("select * from so.get_par_no()")
    Mono<String> getParNo();
}
