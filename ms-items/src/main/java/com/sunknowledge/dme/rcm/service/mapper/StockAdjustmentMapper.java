package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StockAdjustment;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockAdjustment} and its DTO {@link StockAdjustmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StockAdjustmentMapper extends EntityMapper<StockAdjustmentDTO, StockAdjustment> {}
