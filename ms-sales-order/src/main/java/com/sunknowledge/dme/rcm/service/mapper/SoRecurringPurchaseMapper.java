package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SoRecurringPurchase;
import com.sunknowledge.dme.rcm.service.dto.SoRecurringPurchaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoRecurringPurchase} and its DTO {@link SoRecurringPurchaseDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoRecurringPurchaseMapper extends EntityMapper<SoRecurringPurchaseDTO, SoRecurringPurchase> {}
