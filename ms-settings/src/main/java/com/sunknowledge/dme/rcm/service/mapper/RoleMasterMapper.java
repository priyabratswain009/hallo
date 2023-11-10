package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RoleMaster} and its DTO {@link RoleMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface RoleMasterMapper extends EntityMapper<RoleMasterDTO, RoleMaster> {}
