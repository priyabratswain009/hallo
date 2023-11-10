package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PriceMaster;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceMaster} and its DTO {@link PriceMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceMasterMapper extends EntityMapper<PriceMasterDTO, PriceMaster> {}
