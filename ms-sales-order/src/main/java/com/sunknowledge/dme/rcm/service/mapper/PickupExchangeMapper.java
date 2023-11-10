package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PickupExchange} and its DTO {@link PickupExchangeDTO}.
 */
@Mapper(componentModel = "spring")
public interface PickupExchangeMapper extends EntityMapper<PickupExchangeDTO, PickupExchange> {}
