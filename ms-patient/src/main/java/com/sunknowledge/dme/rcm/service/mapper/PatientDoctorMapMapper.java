package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDoctorMap} and its DTO {@link PatientDoctorMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDoctorMapMapper extends EntityMapper<PatientDoctorMapDTO, PatientDoctorMap> {}
