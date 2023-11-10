package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkersCompensationAuditLog} and its DTO {@link WorkersCompensationAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface WorkersCompensationAuditLogMapper extends EntityMapper<WorkersCompensationAuditLogDTO, WorkersCompensationAuditLog> {}
