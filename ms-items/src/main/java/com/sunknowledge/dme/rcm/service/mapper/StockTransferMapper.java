package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StockTransfer;
import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockTransfer} and its DTO {@link StockTransferDTO}.
 */
@Mapper(componentModel = "spring")
public interface StockTransferMapper extends EntityMapper<StockTransferDTO, StockTransfer> {}
