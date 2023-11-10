package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDoctorMapAuditLog} and its DTO {@link PatientDoctorMapAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDoctorMapAuditLogMapper extends EntityMapper<PatientDoctorMapAuditLogDTO, PatientDoctorMapAuditLog> {}
