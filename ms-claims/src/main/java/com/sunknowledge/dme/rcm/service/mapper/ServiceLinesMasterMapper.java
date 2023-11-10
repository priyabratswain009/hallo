package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.domain.ServiceLinesMaster;
import com.sunknowledge.dme.rcm.service.dto.ClaimsSubmissionMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ServiceLinesMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceLinesMaster} and its DTO {@link ServiceLinesMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServiceLinesMasterMapper extends EntityMapper<ServiceLinesMasterDTO, ServiceLinesMaster> {
    @Mapping(
        target = "claimsSubmissionMaster",
        source = "claimsSubmissionMaster",
        qualifiedByName = "claimsSubmissionMasterChangeHealthPrimarySubmisionMasterId"
    )
    ServiceLinesMasterDTO toDto(ServiceLinesMaster s);

    @Named("claimsSubmissionMasterChangeHealthPrimarySubmisionMasterId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "changeHealthPrimarySubmisionMasterId", source = "changeHealthPrimarySubmisionMasterId")
    ClaimsSubmissionMasterDTO toDtoClaimsSubmissionMasterChangeHealthPrimarySubmisionMasterId(
        ClaimsSubmissionMaster claimsSubmissionMaster
    );
}
