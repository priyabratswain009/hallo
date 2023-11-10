package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderItemDetailsAuditLog} and its DTO {@link SalesOrderItemDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderItemDetailsAuditLogMapper
    extends EntityMapper<SalesOrderItemDetailsAuditLogDTO, SalesOrderItemDetailsAuditLog> {}
