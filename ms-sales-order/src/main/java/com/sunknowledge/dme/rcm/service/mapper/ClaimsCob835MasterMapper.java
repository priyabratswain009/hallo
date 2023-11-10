package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCob835Master;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835MasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsCob835Master} and its DTO {@link ClaimsCob835MasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsCob835MasterMapper extends EntityMapper<ClaimsCob835MasterDTO, ClaimsCob835Master> {}
