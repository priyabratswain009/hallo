package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParRequestDetails} and its DTO {@link ParRequestDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParRequestDetailsMapper extends EntityMapper<ParRequestDetailsDTO, ParRequestDetails> {}
