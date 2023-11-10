package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PosMaster;
import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PosMaster} and its DTO {@link PosMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PosMasterMapper extends EntityMapper<PosMasterDTO, PosMaster> {}
