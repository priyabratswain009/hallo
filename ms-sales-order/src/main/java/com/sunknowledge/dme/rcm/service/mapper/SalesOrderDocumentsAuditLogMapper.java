package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderDocumentsAuditLog} and its DTO {@link SalesOrderDocumentsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderDocumentsAuditLogMapper extends EntityMapper<SalesOrderDocumentsAuditLogDTO, SalesOrderDocumentsAuditLog> {}
