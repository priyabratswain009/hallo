package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDocument} and its DTO {@link PatientDocumentDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDocumentMapper extends EntityMapper<PatientDocumentDTO, PatientDocument> {}
