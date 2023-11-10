package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDocumentAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDocumentAuditLog} and its DTO {@link PatientDocumentAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDocumentAuditLogMapper extends EntityMapper<PatientDocumentAuditLogDTO, PatientDocumentAuditLog> {}
