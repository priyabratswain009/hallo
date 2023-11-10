package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.VendorMaster;
import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VendorMaster} and its DTO {@link VendorMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface VendorMasterMapper extends EntityMapper<VendorMasterDTO, VendorMaster> {}
