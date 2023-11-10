package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceGroupMaster} and its DTO {@link InsuranceGroupMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceGroupMasterMapper extends EntityMapper<InsuranceGroupMasterDTO, InsuranceGroupMaster> {}
