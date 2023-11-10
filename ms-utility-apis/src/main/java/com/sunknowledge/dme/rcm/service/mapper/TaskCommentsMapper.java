package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaskComments;
import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskComments} and its DTO {@link TaskCommentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskCommentsMapper extends EntityMapper<TaskCommentsDTO, TaskComments> {}
