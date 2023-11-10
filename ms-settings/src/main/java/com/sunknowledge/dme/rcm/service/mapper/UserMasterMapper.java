package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserMaster} and its DTO {@link UserMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserMasterMapper extends EntityMapper<UserMasterDTO, UserMaster> {}
