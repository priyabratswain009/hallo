package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction;
import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoLcdDocRefTransaction} and its DTO {@link SoLcdDocRefTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoLcdDocRefTransactionMapper extends EntityMapper<SoLcdDocRefTransactionDTO, SoLcdDocRefTransaction> {}
