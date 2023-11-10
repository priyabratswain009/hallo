package com.sunknowledge.dme.rcm.repository.common;

import com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster;
import com.sunknowledge.dme.rcm.repository.MarketingReferalTypeMasterRepository;

import java.util.List;
import java.util.UUID;

public interface MarketingReferalTypeMasterRepositoryExtended extends MarketingReferalTypeMasterRepository {

    List<MarketingReferalTypeMaster> findByStatusIgnoreCase(String active);

    MarketingReferalTypeMaster findByMarketingReferalTypeMasterUUID(UUID marketingReferalTypeMasterUUID);

    List<MarketingReferalTypeMaster> findByMarketingReferalTypeMasterUUIDNot(UUID marketingReferalTypeMasterUUID);
}
