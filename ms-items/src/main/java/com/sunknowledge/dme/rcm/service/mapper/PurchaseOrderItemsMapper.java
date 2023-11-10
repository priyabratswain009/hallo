package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseOrderItems} and its DTO {@link PurchaseOrderItemsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderItemsMapper extends EntityMapper<PurchaseOrderItemsDTO, PurchaseOrderItems> {}
