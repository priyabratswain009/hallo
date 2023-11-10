package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap;
import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RoleFunctionalityMap} and its DTO {@link RoleFunctionalityMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface RoleFunctionalityMapMapper extends EntityMapper<RoleFunctionalityMapDTO, RoleFunctionalityMap> {}
