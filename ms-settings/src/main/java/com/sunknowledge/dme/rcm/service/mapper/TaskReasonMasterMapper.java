package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaskReasonMaster;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskReasonMaster} and its DTO {@link TaskReasonMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskReasonMasterMapper extends EntityMapper<TaskReasonMasterDTO, TaskReasonMaster> {}
