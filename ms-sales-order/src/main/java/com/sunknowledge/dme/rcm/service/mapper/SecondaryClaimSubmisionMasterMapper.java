package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecondaryClaimSubmisionMaster} and its DTO {@link SecondaryClaimSubmisionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecondaryClaimSubmisionMasterMapper
    extends EntityMapper<SecondaryClaimSubmisionMasterDTO, SecondaryClaimSubmisionMaster> {}
