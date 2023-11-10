package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientInsuranceAuditLog} and its DTO {@link PatientInsuranceAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientInsuranceAuditLogMapper extends EntityMapper<PatientInsuranceAuditLogDTO, PatientInsuranceAuditLog> {}
