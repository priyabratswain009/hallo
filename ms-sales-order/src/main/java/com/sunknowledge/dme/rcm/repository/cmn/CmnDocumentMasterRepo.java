package com.sunknowledge.dme.rcm.repository.cmn;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.repository.CmnDocumentMasterRepository;

import reactor.core.publisher.Mono;

public interface CmnDocumentMasterRepo extends CmnDocumentMasterRepository {
    @Query(value = "SELECT * FROM t_cmn_document_master WHERE cmn_id = :cmnId")
    public Mono<CmnDocumentMaster> getCmnDocumentOnCmn(@Param("cmnId") Long cmnId);
    @Query(value = "select tcdm.* from t_cmn tc, t_cmn_document_master tcdm where tc.cmn_id = tcdm.cmn_id and tc.cmn_number = :cmnNumber")
    public Mono<CmnDocumentMaster> getCmnDocumentDetailsOnCmnNumber(@Param("cmnNumber") String cmnNumber);
}
