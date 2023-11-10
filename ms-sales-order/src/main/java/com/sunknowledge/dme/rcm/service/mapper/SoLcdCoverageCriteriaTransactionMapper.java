package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction;
import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoLcdCoverageCriteriaTransaction} and its DTO {@link SoLcdCoverageCriteriaTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoLcdCoverageCriteriaTransactionMapper
    extends EntityMapper<SoLcdCoverageCriteriaTransactionDTO, SoLcdCoverageCriteriaTransaction> {}
