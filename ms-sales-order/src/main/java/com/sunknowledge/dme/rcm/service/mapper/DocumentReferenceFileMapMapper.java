package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap;
import com.sunknowledge.dme.rcm.service.dto.DocumentReferenceFileMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentReferenceFileMap} and its DTO {@link DocumentReferenceFileMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentReferenceFileMapMapper extends EntityMapper<DocumentReferenceFileMapDTO, DocumentReferenceFileMap> {}
