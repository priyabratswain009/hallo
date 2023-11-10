package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderFinancialDetailsAuditLog} and its DTO {@link SalesOrderFinancialDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderFinancialDetailsAuditLogMapper
    extends EntityMapper<SalesOrderFinancialDetailsAuditLogDTO, SalesOrderFinancialDetailsAuditLog> {}
