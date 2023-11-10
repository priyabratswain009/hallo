package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryItems} and its DTO {@link DeliveryItemsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryItemsMapper extends EntityMapper<DeliveryItemsDTO, DeliveryItems> {}
