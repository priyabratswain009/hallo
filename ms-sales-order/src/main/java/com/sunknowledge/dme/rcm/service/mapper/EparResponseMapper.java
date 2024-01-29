package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.service.dto.EparResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EparResponse} and its DTO {@link EparResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface EparResponseMapper extends EntityMapper<EparResponseDTO, EparResponse> {}
