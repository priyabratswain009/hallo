package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchItemLocationMapAuditLog} and its DTO {@link BranchItemLocationMapAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchItemLocationMapAuditLogMapper
    extends EntityMapper<BranchItemLocationMapAuditLogDTO, BranchItemLocationMapAuditLog> {}
