package com.sunknowledge.dme.rcm.repository.others;

import com.sunknowledge.dme.rcm.domain.TaxZone;
import com.sunknowledge.dme.rcm.repository.TaxZoneRepository;

import java.util.List;

public interface TaxZoneRepositoryExtended extends TaxZoneRepository {
    List<TaxZone> findByStateNameIgnoreCaseAndStatusIgnoreCase(String valueOf, String active);

    TaxZone findByStateCodeIdAndStatusIgnoreCase(Long valueOf, String active);

    TaxZone findByStateCodeIgnoreCaseAndStatusIgnoreCase(String param, String active);

    List<TaxZone> findByStatusIgnoreCase(String active);

    TaxZone findBytaxZoneId(Long taxZoneId);

    List<TaxZone> findByStateNameLikeIgnoreCaseAndStatusIgnoreCase(String s, String active);

    List<TaxZone> findByStatusIgnoreCaseOrderByStateNameAsc(String active);
}
