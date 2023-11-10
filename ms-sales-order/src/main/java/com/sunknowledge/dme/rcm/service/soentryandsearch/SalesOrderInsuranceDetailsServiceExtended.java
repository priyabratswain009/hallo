package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceEntryParameterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderInsuranceDetailsServiceExtended extends SalesOrderInsuranceDetailsService {
    Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(UUID sOInsuranceDetailsUUID);

    Long getIDByUUID(UUID sOInsuranceDetailsUUID);

    Mono<SalesOrderInsuranceDetails> findById(Long id);

    Mono<ResponseDTO> saveSOInsuranceDetails(SalesOrderInsuranceDetailsDTO obj, SalesOrderInsuranceEntryParameterDTO salesOrderInsuranceEntryParameterDTO, Long branchId);
    Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSOSecondaryInsuranceInfo(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO);
    Mono<ServiceOutcome> updateSOInsuranceStatus(Long soInsId);
}
