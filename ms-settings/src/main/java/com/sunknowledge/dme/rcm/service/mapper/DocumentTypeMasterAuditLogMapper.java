package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentTypeMasterAuditLog} and its DTO {@link DocumentTypeMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentTypeMasterAuditLogMapper extends EntityMapper<DocumentTypeMasterAuditLogDTO, DocumentTypeMasterAuditLog> {}
