package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster;
import com.sunknowledge.dme.rcm.service.dto.DmeGroupChecklistMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DmeGroupChecklistMaster} and its DTO {@link DmeGroupChecklistMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmeGroupChecklistMasterMapper extends EntityMapper<DmeGroupChecklistMasterDTO, DmeGroupChecklistMaster> {}
