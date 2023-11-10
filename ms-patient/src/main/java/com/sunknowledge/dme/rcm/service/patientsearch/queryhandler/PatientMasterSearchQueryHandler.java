package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByCombinedInfo;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientMasterSearchQueryHandler {
    Flux<PatientMasterDTO> getPatientBySearchParametersQueryHandler(PatientSearchByBasicInfoOrAddressOrBranch obj);

    Flux<PatientMasterDTO> checkPatientIsAlreadyExistOrNotQueryHandler(CheckingPatientExistanceByNameAndZipAndBranchAndDob obj);

    Flux<PatientMasterDTO> getPatientByPatientIdNoQueryHandler(PatientSearchByPatientIdNo obj);

    String generatePatientIDNumber();

    Mono<Long> getIDByUUID(UUID patientUuid);

    Mono<PatientMasterDTO> getPatientByPatientIdQueryHandler(PatientSearchByPatientId obj);

    Flux<PatientCombinedSearchOutputDTO> getPatientByCombinedSearchParameters(PatientSearchByCombinedInfo obj);


    String getPosNameById(Long posId);

    Mono<PatientMasterDTO> getPatientByPatientId(Long patientId);

    Mono<String> getBranchNameByBranchId(Long branchId);
}
