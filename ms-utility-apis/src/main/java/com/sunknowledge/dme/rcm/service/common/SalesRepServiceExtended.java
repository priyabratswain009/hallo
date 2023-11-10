package com.sunknowledge.dme.rcm.service.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.SalesRepService;
import com.sunknowledge.dme.rcm.service.dto.common.SalesRepParameterDTO;

import java.util.UUID;

public interface SalesRepServiceExtended extends SalesRepService {
    ServiceOutcome saveSalesRep(SalesRepParameterDTO salesRepParameterDTO);

    ServiceOutcome getSalesRepByNameOrNoOrUUID(String data, String operationType);

    Long getIDByUUID(UUID randomUUID);

    ServiceOutcome getSalesRepInfoByUUID(UUID salesRepUuid);

    ServiceOutcome getSalesRepInfoByName(String name);
}
