package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceSearchByPatientIdAndInsuranceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PatientInsuranceSearchServiceExtended {

    Flux<PatientInsuranceDTO> getPatientInsuranceBySearchParameters(PatientInsuranceSearchByPatientIdAndInsuranceId obj);
    Flux<PatientInsuranceDTO> getPatientInsuranceByPatientId(Long patientId);

    Mono<Long> getIDByUUID(UUID patientUuid);

    Mono<Object> getPatientInsuranceByPatInsuranceId(PatientInsuranceSearchByPatientIdAndInsuranceId obj);

    Mono<PatientInsuranceDTO> getPatientInsuranceByPatientUUIDAndPayerLevel(PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel);

    Mono<ServiceOutcome> getExixtingPayerLevelsByPatientUUID(Long patientId) throws ExecutionException, InterruptedException;

    Mono<PatientInsuranceDTO> getPatientInsuranceInfoByPatientInsId(Long patientInsId);

    Mono<Long> getPatientIdByPatientInsuranceUUID(UUID patientInsuranceUUID);
}
