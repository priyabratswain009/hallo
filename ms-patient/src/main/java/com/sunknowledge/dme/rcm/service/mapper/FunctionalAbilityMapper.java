package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.FunctionalAbility;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FunctionalAbility} and its DTO {@link FunctionalAbilityDTO}.
 */
@Mapper(componentModel = "spring")
public interface FunctionalAbilityMapper extends EntityMapper<FunctionalAbilityDTO, FunctionalAbility> {}
