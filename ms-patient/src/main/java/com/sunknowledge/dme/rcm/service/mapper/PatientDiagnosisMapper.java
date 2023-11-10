package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDiagnosis} and its DTO {@link PatientDiagnosisDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDiagnosisMapper extends EntityMapper<PatientDiagnosisDTO, PatientDiagnosis> {}
