package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.RoleUserMap;
import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RoleUserMap} and its DTO {@link RoleUserMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface RoleUserMapMapper extends EntityMapper<RoleUserMapDTO, RoleUserMap> {}
