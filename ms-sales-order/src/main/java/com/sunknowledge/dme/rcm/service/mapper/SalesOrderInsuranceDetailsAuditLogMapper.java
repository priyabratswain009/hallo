package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderInsuranceDetailsAuditLog} and its DTO {@link SalesOrderInsuranceDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderInsuranceDetailsAuditLogMapper
    extends EntityMapper<SalesOrderInsuranceDetailsAuditLogDTO, SalesOrderInsuranceDetailsAuditLog> {}
