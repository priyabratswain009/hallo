package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceLineItemDetails} and its DTO {@link InvoiceLineItemDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoiceLineItemDetailsMapper extends EntityMapper<InvoiceLineItemDetailsDTO, InvoiceLineItemDetails> {}
