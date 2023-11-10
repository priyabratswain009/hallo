package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDiagnosisAuditLog} and its DTO {@link PatientDiagnosisAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDiagnosisAuditLogMapper extends EntityMapper<PatientDiagnosisAuditLogDTO, PatientDiagnosisAuditLog> {}
