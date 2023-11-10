package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DoctorMaster;
import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoctorMaster} and its DTO {@link DoctorMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface DoctorMasterMapper extends EntityMapper<DoctorMasterDTO, DoctorMaster> {}
