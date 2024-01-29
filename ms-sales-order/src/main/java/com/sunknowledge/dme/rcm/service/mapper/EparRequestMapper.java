package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EparRequest} and its DTO {@link EparRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface EparRequestMapper extends EntityMapper<EparRequestDTO, EparRequest> {}
