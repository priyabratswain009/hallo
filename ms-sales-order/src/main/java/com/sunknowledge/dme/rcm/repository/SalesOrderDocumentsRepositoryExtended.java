package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderDocumentsRepositoryExtended extends SalesOrderDocumentsRepository{
    Flux<SalesOrderDocuments> findBySalesOrderId(Long SOID);

    Flux<SalesOrderDocuments> getSODocumentsBySODocumentUUID(UUID sODocumentsUUID);
    @Query(value="select so.get_t_sales_order_documents_id_by_uuid(:sODocumentsUUID)")
    Mono<Long> getIDByUUID(@Param("sODocumentsUUID") UUID sODocumentsUUID);
}
