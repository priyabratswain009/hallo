package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.IcdMaster;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link IcdMaster} and its DTO {@link IcdMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface IcdMasterMapper extends EntityMapper<IcdMasterDTO, IcdMaster> {}
