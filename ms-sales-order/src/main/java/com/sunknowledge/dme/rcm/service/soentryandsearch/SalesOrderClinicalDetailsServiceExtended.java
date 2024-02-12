package com.sunknowledge.dme.rcm.service.soentryandsearch;

import java.util.Map;
import java.util.UUID;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.service.SalesOrderClinicalDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderClinicalEntryParameterDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SalesOrderClinicalDetailsServiceExtended extends SalesOrderClinicalDetailsService {
    Flux<SalesOrderClinicalDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderClinicalDetails> getSOClinicalByUUID(UUID sOClinicalUUID);

    Long getIDByUUID(UUID sOClinicalUUID);

    Mono<SalesOrderClinicalDetails> findByClinicalId(Long id);

    Mono<ServiceOutcome> saveSOClinicalDetails(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO, Long soId, SalesOrderMasterDTO salesOrderMasterDTO);

    ResponseDTO getDoctorMasterDataFromPatient(Long patientId, String npiId);
    ResponseDTO getICDCodeMasterDataFromUtility(String icdCodes, String icdCodeType);

    Map<String, Object> validateSOClinicalParameterDTO(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO);

    Mono<ServiceOutcome> getSOClinicalBySOID(Long salesOrderID);
}
