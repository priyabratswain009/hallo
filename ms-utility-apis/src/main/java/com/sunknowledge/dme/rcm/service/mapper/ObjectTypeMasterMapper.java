package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ObjectTypeMaster;
import com.sunknowledge.dme.rcm.service.dto.ObjectTypeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjectTypeMaster} and its DTO {@link ObjectTypeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ObjectTypeMasterMapper extends EntityMapper<ObjectTypeMasterDTO, ObjectTypeMaster> {}
