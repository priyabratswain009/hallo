package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoItemTransactionDetails} and its DTO {@link SoItemTransactionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoItemTransactionDetailsMapper extends EntityMapper<SoItemTransactionDetailsDTO, SoItemTransactionDetails> {}
