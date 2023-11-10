package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecondaryClaimsSubmissionMaster} and its DTO {@link SecondaryClaimsSubmissionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecondaryClaimsSubmissionMasterMapper
    extends EntityMapper<SecondaryClaimsSubmissionMasterDTO, SecondaryClaimsSubmissionMaster> {}
