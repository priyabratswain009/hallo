package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsurancePricetableMap} and its DTO {@link InsurancePricetableMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsurancePricetableMapMapper extends EntityMapper<InsurancePricetableMapDTO, InsurancePricetableMap> {}
