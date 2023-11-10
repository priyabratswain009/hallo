package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceMasterDetails} and its DTO {@link InvoiceMasterDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoiceMasterDetailsMapper extends EntityMapper<InvoiceMasterDetailsDTO, InvoiceMasterDetails> {}
