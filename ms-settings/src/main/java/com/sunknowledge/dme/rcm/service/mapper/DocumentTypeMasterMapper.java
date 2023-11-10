package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMaster;
import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentTypeMaster} and its DTO {@link DocumentTypeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentTypeMasterMapper extends EntityMapper<DocumentTypeMasterDTO, DocumentTypeMaster> {}
