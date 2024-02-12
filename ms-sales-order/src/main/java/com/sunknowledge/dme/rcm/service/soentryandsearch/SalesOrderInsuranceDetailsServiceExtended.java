package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.service.SalesOrderInsuranceDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceEntryParameterDTO;
import org.checkerframework.checker.nullness.Opt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderInsuranceDetailsServiceExtended extends SalesOrderInsuranceDetailsService {
    Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(UUID sOInsuranceDetailsUUID);

    Long getIDByUUID(UUID sOInsuranceDetailsUUID);
    Mono<Long> getIDByUUIDReactive(UUID sOInsuranceDetailsUUID);

    Mono<SalesOrderInsuranceDetails> findById(Long id);

    Mono<ResponseDTO> saveSOInsuranceDetails(SalesOrderInsuranceDetailsDTO obj, SalesOrderInsuranceEntryParameterDTO salesOrderInsuranceEntryParameterDTO, Long branchId);
    Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSOSecondaryInsuranceInfo(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO);
    Mono<ServiceOutcome> updateSOInsuranceStatus(Long soInsId);

    Mono<SalesOrderInsuranceDetailsDTO> verifySOInsuranceManually(Long soInsuranceId, String insuranceVerificationStatus, String payerLevel);

    Mono<SalesOrderInsuranceDetails> findBySOId(Long soId);

    Mono<SalesOrderInsuranceDetailsDTO> verifySOInsuranceAutomatic(SalesOrderMasterDTO salesOrderMaster, SalesOrderInsuranceDetails salesOrderInsuranceDetails, String payerLevel, String accessToken);

    Mono<ServiceOutcome<String>> getServiceReviewById(long id);

    Mono<SalesOrderInsuranceDetailsDTO> updateCoverageVerificationStatus(Long soInsuranceId, String coverageVerificationStatus);
}
