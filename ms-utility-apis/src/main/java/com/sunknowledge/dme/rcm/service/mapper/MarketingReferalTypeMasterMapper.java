package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MarketingReferalTypeMaster} and its DTO {@link MarketingReferalTypeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface MarketingReferalTypeMasterMapper extends EntityMapper<MarketingReferalTypeMasterDTO, MarketingReferalTypeMaster> {}
