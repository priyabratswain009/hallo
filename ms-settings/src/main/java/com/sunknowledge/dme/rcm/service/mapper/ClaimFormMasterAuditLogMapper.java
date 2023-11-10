package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimFormMasterAuditLog} and its DTO {@link ClaimFormMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimFormMasterAuditLogMapper extends EntityMapper<ClaimFormMasterAuditLogDTO, ClaimFormMasterAuditLog> {}
