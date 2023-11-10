package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseOrderItemsReceived} and its DTO {@link PurchaseOrderItemsReceivedDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderItemsReceivedMapper extends EntityMapper<PurchaseOrderItemsReceivedDTO, PurchaseOrderItemsReceived> {}
