package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDto} and its DTO {@link PatientDtoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDtoMapper extends EntityMapper<PatientDtoDTO, PatientDto> {}
