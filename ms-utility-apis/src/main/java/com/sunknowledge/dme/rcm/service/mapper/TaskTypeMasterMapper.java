package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaskTypeMaster;
import com.sunknowledge.dme.rcm.service.dto.TaskTypeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskTypeMaster} and its DTO {@link TaskTypeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskTypeMasterMapper extends EntityMapper<TaskTypeMasterDTO, TaskTypeMaster> {}
