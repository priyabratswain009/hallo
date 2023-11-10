package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryClaimsReSubmissionMaster} and its DTO {@link PrimaryClaimsReSubmissionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryClaimsReSubmissionMasterMapper
    extends EntityMapper<PrimaryClaimsReSubmissionMasterDTO, PrimaryClaimsReSubmissionMaster> {}
