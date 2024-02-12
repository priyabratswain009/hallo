package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.EfaxResponse;
import com.sunknowledge.dme.rcm.service.dto.EfaxResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EfaxResponse} and its DTO {@link EfaxResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface EfaxResponseMapper extends EntityMapper<EfaxResponseDTO, EfaxResponse> {}
