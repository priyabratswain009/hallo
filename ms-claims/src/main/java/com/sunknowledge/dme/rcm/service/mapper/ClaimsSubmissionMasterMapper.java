package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.service.dto.ClaimsSubmissionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsSubmissionMaster} and its DTO {@link ClaimsSubmissionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsSubmissionMasterMapper extends EntityMapper<ClaimsSubmissionMasterDTO, ClaimsSubmissionMaster> {}
