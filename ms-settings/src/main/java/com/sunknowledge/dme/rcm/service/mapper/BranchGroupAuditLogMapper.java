package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchGroupAuditLog} and its DTO {@link BranchGroupAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchGroupAuditLogMapper extends EntityMapper<BranchGroupAuditLogDTO, BranchGroupAuditLog> {}
