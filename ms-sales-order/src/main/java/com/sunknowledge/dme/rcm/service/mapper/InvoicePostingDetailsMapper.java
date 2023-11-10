package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetails;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoicePostingDetails} and its DTO {@link InvoicePostingDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoicePostingDetailsMapper extends EntityMapper<InvoicePostingDetailsDTO, InvoicePostingDetails> {}
