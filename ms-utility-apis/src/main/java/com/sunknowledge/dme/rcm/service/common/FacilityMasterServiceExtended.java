package com.sunknowledge.dme.rcm.service.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.FacilityMasterService;
import com.sunknowledge.dme.rcm.service.dto.common.FacilityMasterParameterDTO;

import java.util.UUID;

public interface FacilityMasterServiceExtended extends FacilityMasterService {
    ServiceOutcome saveFacilityMaster(FacilityMasterParameterDTO facilityMasterParameterDTO);

    ServiceOutcome getFacilityMasterByNameOrNoOrUUID(String data, String operationType);

    Long getIDByUUID(UUID l);
}
