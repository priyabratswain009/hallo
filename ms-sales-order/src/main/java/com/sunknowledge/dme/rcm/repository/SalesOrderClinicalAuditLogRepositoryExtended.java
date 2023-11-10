package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SalesOrderClinicalAuditLogRepositoryExtended extends SalesOrderClinicalDetailsAuditLogRepository{
    Flux<SalesOrderClinicalDetailsAuditLog> findBySalsOdrClincalDetilsId(List SOClinicalIDs);
}
