package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaskDetails;
import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskDetails} and its DTO {@link TaskDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskDetailsMapper extends EntityMapper<TaskDetailsDTO, TaskDetails> {}
