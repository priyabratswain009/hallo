package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderClassificationAuditLog} and its DTO {@link SalesOrderClassificationAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderClassificationAuditLogMapper
    extends EntityMapper<SalesOrderClassificationAuditLogDTO, SalesOrderClassificationAuditLog> {}
