package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.FunctionalityMasterService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityMasterParameterDTO;

import java.util.List;
import java.util.UUID;

public interface FunctionalityMasterServiceExtended extends FunctionalityMasterService {
    ResponseDTO saveFunctionalityMaster(FunctionalityMasterParameterDTO functionalityMasterParameterDTO);

    ResponseDTO getFunctionalityMasterByNameOrNoOrUUID(String data, String operationType);

    Long getIDByUUID(UUID d);

    List<Long> getActiveIDsByUUIDs(List<UUID> functionalityUUIDs);
}
