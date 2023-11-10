package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimProgramMasterAuditLog} and its DTO {@link ClaimProgramMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimProgramMasterAuditLogMapper extends EntityMapper<ClaimProgramMasterAuditLogDTO, ClaimProgramMasterAuditLog> {}
