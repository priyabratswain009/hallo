package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderMasterAuditLog} and its DTO {@link SalesOrderMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderMasterAuditLogMapper extends EntityMapper<SalesOrderMasterAuditLogDTO, SalesOrderMasterAuditLog> {}
