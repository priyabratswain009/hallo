package com.sunknowledge.dme.rcm.repository.pricetable;

import com.sunknowledge.dme.rcm.domain.PriceMaster;
import com.sunknowledge.dme.rcm.repository.PriceMasterRepository;

import java.util.List;

public interface PriceMasterRepositoryExtended extends PriceMasterRepository {
    List<PriceMaster> findByStatusIgnoreCase(String active);
}
