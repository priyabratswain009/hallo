package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchInsuranceMapAuditLog} and its DTO {@link BranchInsuranceMapAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchInsuranceMapAuditLogMapper extends EntityMapper<BranchInsuranceMapAuditLogDTO, BranchInsuranceMapAuditLog> {}
