package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformation;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientClinicalInformation} and its DTO {@link PatientClinicalInformationDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientClinicalInformationMapper extends EntityMapper<PatientClinicalInformationDTO, PatientClinicalInformation> {}
