package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.service.dto.ParSoMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParSoMap} and its DTO {@link ParSoMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParSoMapMapper extends EntityMapper<ParSoMapDTO, ParSoMap> {}
