package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStat;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientInsVerifStat} and its DTO {@link PatientInsVerifStatDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientInsVerifStatMapper extends EntityMapper<PatientInsVerifStatDTO, PatientInsVerifStat> {}
