package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.EndpointMasterService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.EndpointMasterParameterDTO;

import java.util.List;
import java.util.UUID;

public interface EndpointMasterServiceExtended extends EndpointMasterService {
    ResponseDTO saveUpdateEndpointMaster(EndpointMasterParameterDTO endpointMasterParameterDTO);

    Long getIDByUUID(UUID endpointMasterUuid);

    ResponseDTO getEndpointMasterByNameOrGroupOrUUID(String data, String operationType);

    List<Long> getActiveIDsByUUIDs(List<UUID> endpointUUIDs);
}
