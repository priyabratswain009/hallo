package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CmnDocumentMaster} and its DTO {@link CmnDocumentMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface CmnDocumentMasterMapper extends EntityMapper<CmnDocumentMasterDTO, CmnDocumentMaster> {}
