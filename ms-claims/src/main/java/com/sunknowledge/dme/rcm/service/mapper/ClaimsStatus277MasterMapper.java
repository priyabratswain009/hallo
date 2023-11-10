package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsStatus277Master;
import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277MasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsStatus277Master} and its DTO {@link ClaimsStatus277MasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsStatus277MasterMapper extends EntityMapper<ClaimsStatus277MasterDTO, ClaimsStatus277Master> {}
