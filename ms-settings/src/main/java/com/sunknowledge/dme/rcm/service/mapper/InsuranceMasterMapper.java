package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceMaster;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceMaster} and its DTO {@link InsuranceMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceMasterMapper extends EntityMapper<InsuranceMasterDTO, InsuranceMaster> {}
