package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SalesOrderDocumentsAuditLogRepositoryExtended extends SalesOrderDocumentsAuditLogRepository{
    Flux<SalesOrderDocumentsAuditLog> findBySalesOrderDocId(List SODocumentsIDs);
}
