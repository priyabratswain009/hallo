package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SalesOrderItemAuditLogRepositoryExtended extends SalesOrderItemDetailsAuditLogRepository{
    Flux<SalesOrderItemDetailsAuditLog> findBySalsOrdrItemDetailId(List SOItemIDs);
}
