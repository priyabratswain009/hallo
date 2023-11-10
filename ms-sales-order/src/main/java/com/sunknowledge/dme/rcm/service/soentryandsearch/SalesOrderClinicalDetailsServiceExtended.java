package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.service.SalesOrderClinicalDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderClinicalEntryParameterDTO;
import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

public interface SalesOrderClinicalDetailsServiceExtended extends SalesOrderClinicalDetailsService {
    Flux<SalesOrderClinicalDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderClinicalDetails> getSOClinicalByUUID(UUID sOClinicalUUID);

    Long getIDByUUID(UUID sOClinicalUUID);

    Mono<SalesOrderClinicalDetails> findByClinicalId(Long id);

    Mono<ServiceOutcome> saveSOClinicalDetails(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO);

    ResponseDTO getDoctorMasterDataFromPatient(Long patientId, String npiId);
    ResponseDTO getICDCodeMasterDataFromUtility(String icdCodes, String icdCodeType);

    Map<String, Object> validateSOClinicalParameterDTO(SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO);
}
