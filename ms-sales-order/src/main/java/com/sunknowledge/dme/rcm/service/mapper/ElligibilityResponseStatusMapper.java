package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponseStatus;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElligibilityResponseStatus} and its DTO {@link ElligibilityResponseStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ElligibilityResponseStatusMapper extends EntityMapper<ElligibilityResponseStatusDTO, ElligibilityResponseStatus> {}
