package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceDocumentAuditLog} and its DTO {@link InsuranceDocumentAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceDocumentAuditLogMapper extends EntityMapper<InsuranceDocumentAuditLogDTO, InsuranceDocumentAuditLog> {}
