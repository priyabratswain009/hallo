package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceDocument;
import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceDocument} and its DTO {@link InsuranceDocumentDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceDocumentMapper extends EntityMapper<InsuranceDocumentDTO, InsuranceDocument> {}
