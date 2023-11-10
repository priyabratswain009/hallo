package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap;
import com.sunknowledge.dme.rcm.service.dto.ChecklistDocumentReferenceMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChecklistDocumentReferenceMap} and its DTO {@link ChecklistDocumentReferenceMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChecklistDocumentReferenceMapMapper
    extends EntityMapper<ChecklistDocumentReferenceMapDTO, ChecklistDocumentReferenceMap> {}
