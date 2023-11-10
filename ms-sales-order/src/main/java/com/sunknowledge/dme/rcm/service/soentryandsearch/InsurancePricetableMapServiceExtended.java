package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.service.InsurancePricetableMapService;
import reactor.core.publisher.Flux;

public interface InsurancePricetableMapServiceExtended extends InsurancePricetableMapService {
    Flux<InsurancePricetableMap> findInsurancePricetableMapDetailsByInsuranceIdNo(String insuranceIdNo);

    Flux<InsurancePricetableMap> findInsurancePricetableMapDetailsByInsuranceId(Long insuranceId);
}
