package com.sunknowledge.dme.rcm.service.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.MarketingReferalTypeMasterService;
import com.sunknowledge.dme.rcm.service.dto.common.MarketingReferalTypeMasterExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.UUID;

public interface MarketingReferalTypeMasterServiceExtended extends MarketingReferalTypeMasterService {
    ServiceOutcome saveMarketingReferalTypeMaster(MarketingReferalTypeMasterExtendedDTO marketingReferalTypeMasterExtendedDTO) throws InvalidAttributeValueException;
    ServiceOutcome getAllMarketingReferalTypeMasterInfo();
    ServiceOutcome getMarketingReferalTypeMasterInfoByUUID(UUID marketingReferalTypeMasterUUID);
}
