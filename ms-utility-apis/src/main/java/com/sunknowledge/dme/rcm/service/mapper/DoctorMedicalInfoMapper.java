package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo;
import com.sunknowledge.dme.rcm.service.dto.DoctorMedicalInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoctorMedicalInfo} and its DTO {@link DoctorMedicalInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DoctorMedicalInfoMapper extends EntityMapper<DoctorMedicalInfoDTO, DoctorMedicalInfo> {}
