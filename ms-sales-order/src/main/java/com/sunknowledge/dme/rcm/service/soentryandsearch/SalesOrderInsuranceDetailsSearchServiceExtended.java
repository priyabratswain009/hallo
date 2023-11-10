package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import reactor.core.publisher.Flux;

public interface SalesOrderInsuranceDetailsSearchServiceExtended extends SalesOrderInsuranceDetailsService {
    Flux<SalesOrderInsuranceSearchDetailsDTO> getSOInsuranceDetailsByInsuranceName(String insuranceName);

    Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByInsuranceId(Long insuranceId);
}
