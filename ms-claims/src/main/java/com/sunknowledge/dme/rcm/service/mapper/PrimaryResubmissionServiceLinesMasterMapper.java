package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimsReSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.PrimaryResubmissionServiceLinesMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryResubmissionServiceLinesMaster} and its DTO {@link PrimaryResubmissionServiceLinesMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryResubmissionServiceLinesMasterMapper
    extends EntityMapper<PrimaryResubmissionServiceLinesMasterDTO, PrimaryResubmissionServiceLinesMaster> {
    @Mapping(
        target = "primaryClaimsReSubmissionMaster",
        source = "primaryClaimsReSubmissionMaster",
        qualifiedByName = "primaryClaimsReSubmissionMasterChangeHealthPrimaryResubmisionMasterId"
    )
    PrimaryResubmissionServiceLinesMasterDTO toDto(PrimaryResubmissionServiceLinesMaster s);

    @Named("primaryClaimsReSubmissionMasterChangeHealthPrimaryResubmisionMasterId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "changeHealthPrimaryResubmisionMasterId", source = "changeHealthPrimaryResubmisionMasterId")
    PrimaryClaimsReSubmissionMasterDTO toDtoPrimaryClaimsReSubmissionMasterChangeHealthPrimaryResubmisionMasterId(
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster
    );
}
