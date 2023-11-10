package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientInsurance;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientInsurance} and its DTO {@link PatientInsuranceDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientInsuranceMapper extends EntityMapper<PatientInsuranceDTO, PatientInsurance> {}
