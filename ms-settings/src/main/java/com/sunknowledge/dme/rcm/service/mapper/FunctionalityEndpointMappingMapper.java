package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FunctionalityEndpointMapping} and its DTO {@link FunctionalityEndpointMappingDTO}.
 */
@Mapper(componentModel = "spring")
public interface FunctionalityEndpointMappingMapper extends EntityMapper<FunctionalityEndpointMappingDTO, FunctionalityEndpointMapping> {}
