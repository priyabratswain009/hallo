package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails;
import com.sunknowledge.dme.rcm.service.dto.ReceiptMasterDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReceiptMasterDetails} and its DTO {@link ReceiptMasterDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReceiptMasterDetailsMapper extends EntityMapper<ReceiptMasterDetailsDTO, ReceiptMasterDetails> {}
