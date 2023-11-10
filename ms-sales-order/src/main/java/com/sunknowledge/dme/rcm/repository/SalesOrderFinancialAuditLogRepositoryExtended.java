package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SalesOrderFinancialAuditLogRepositoryExtended extends SalesOrderFinancialDetailsAuditLogRepository{
    Flux<SalesOrderFinancialDetailsAuditLog> findBySalsOrdrFincialId(List SOFinancialIDs);
}
