package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimsSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.SecondaryServiceLinesMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecondaryServiceLinesMaster} and its DTO {@link SecondaryServiceLinesMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecondaryServiceLinesMasterMapper extends EntityMapper<SecondaryServiceLinesMasterDTO, SecondaryServiceLinesMaster> {
    @Mapping(
        target = "secondaryClaimsSubmissionMaster",
        source = "secondaryClaimsSubmissionMaster",
        qualifiedByName = "secondaryClaimsSubmissionMasterChangeHealthSecondarySubmisionMasterId"
    )
    SecondaryServiceLinesMasterDTO toDto(SecondaryServiceLinesMaster s);

    @Named("secondaryClaimsSubmissionMasterChangeHealthSecondarySubmisionMasterId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "changeHealthSecondarySubmisionMasterId", source = "changeHealthSecondarySubmisionMasterId")
    SecondaryClaimsSubmissionMasterDTO toDtoSecondaryClaimsSubmissionMasterChangeHealthSecondarySubmisionMasterId(
        SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster
    );
}
