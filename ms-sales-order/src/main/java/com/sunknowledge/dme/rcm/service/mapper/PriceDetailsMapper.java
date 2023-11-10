package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PriceDetails;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceDetails} and its DTO {@link PriceDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceDetailsMapper extends EntityMapper<PriceDetailsDTO, PriceDetails> {}
