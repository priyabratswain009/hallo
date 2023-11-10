package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientMaster;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientMaster} and its DTO {@link PatientMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientMasterMapper extends EntityMapper<PatientMasterDTO, PatientMaster> {}
