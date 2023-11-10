package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PickupExchangeItems} and its DTO {@link PickupExchangeItemsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PickupExchangeItemsMapper extends EntityMapper<PickupExchangeItemsDTO, PickupExchangeItems> {}
