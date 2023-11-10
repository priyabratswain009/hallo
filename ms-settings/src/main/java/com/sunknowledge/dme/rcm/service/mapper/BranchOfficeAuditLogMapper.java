package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchOfficeAuditLog} and its DTO {@link BranchOfficeAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchOfficeAuditLogMapper extends EntityMapper<BranchOfficeAuditLogDTO, BranchOfficeAuditLog> {}
