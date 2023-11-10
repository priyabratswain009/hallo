package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SalesOrderInsuranceAuditLogRepositoryExtended extends SalesOrderInsuranceDetailsAuditLogRepository{
    Flux<SalesOrderInsuranceDetailsAuditLog> findBySalsOrdInsranceDetailsId(List SOInsuranceIDs);
}
