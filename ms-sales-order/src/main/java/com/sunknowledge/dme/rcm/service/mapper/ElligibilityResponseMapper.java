package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ElligibilityResponse;
import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElligibilityResponse} and its DTO {@link ElligibilityResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ElligibilityResponseMapper extends EntityMapper<ElligibilityResponseDTO, ElligibilityResponse> {}
