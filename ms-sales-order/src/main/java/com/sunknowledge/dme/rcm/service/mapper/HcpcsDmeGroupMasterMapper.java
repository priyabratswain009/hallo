package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster;
import com.sunknowledge.dme.rcm.service.dto.HcpcsDmeGroupMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HcpcsDmeGroupMaster} and its DTO {@link HcpcsDmeGroupMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface HcpcsDmeGroupMasterMapper extends EntityMapper<HcpcsDmeGroupMasterDTO, HcpcsDmeGroupMaster> {}
