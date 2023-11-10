package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PriceDetailsMaster;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceDetailsMaster} and its DTO {@link PriceDetailsMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceDetailsMasterMapper extends EntityMapper<PriceDetailsMasterDTO, PriceDetailsMaster> {}
