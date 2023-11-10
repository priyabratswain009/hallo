package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientBucketParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientExistCheckingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByCombinedInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientMasterSearchServiceExtended {

    Flux<PatientMasterDTO> getPatientBySearchParameters(PatientSearchByBasicInfoOrAddressOrBranch obj);

    Flux<PatientMasterDTO> checkPatientIsAlreadyExistOrNot(PatientExistCheckingParameterDTO patientExistCheckingParameterDTO);

    Flux<PatientMasterDTO> getPatientByPatientIdNo(String patientIdNo);

    String generatePatientIDNumber();

    Flux<PatientDtoDTO> getPatientBucketData(PatientBucketParameterDTO patientBucketParameterDTO);

    Mono<Long> getIDByUUID(UUID patientUuid);

    Flux<PatientCombinedSearchOutputDTO> getPatientByCombinedSearchParameters(PatientSearchByCombinedInfo obj);

    Mono<PatientMasterDTO> getPatientDetailsByPatientId(Long id);

    String getPosNameById(Long posId);

    Mono<PatientMasterDTO> getPatientByPatientId(Long patientId);

    Mono<ServiceOutcome> checkPatientBillingAddressVerifiableOrNot(PatientMasterDTO patientData);

    Mono<Boolean> checkSameAsDeliveryAddress(PatientMasterDTO patientData);

    Mono<String> getBranchNameByBranchId(Long branchId);
}
