package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientDocumentSoMap} and its DTO {@link PatientDocumentSoMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientDocumentSoMapMapper extends EntityMapper<PatientDocumentSoMapDTO, PatientDocumentSoMap> {}
