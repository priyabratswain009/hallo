package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocuments;
import com.sunknowledge.dme.rcm.service.SalesOrderDocumentsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderDocumentsServiceExtended extends SalesOrderDocumentsService {
    Flux<SalesOrderDocuments> findBySalesOrderId(Long SOID);

    Flux<SalesOrderDocuments> getSODocumentsBySODocumentUUID(UUID sODocumentsUUID);

    Long getIDByUUID(UUID sODocumentsUUID);

    Mono<SalesOrderDocuments> findByDocumentId(Long id);
}
