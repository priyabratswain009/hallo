package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.EndpointMaster;
import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EndpointMaster} and its DTO {@link EndpointMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface EndpointMasterMapper extends EntityMapper<EndpointMasterDTO, EndpointMaster> {}
