package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceSearchByPatientIdAndInsuranceId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PatientInsuranceSearchQueryHandler {
    Flux<PatientInsuranceDTO> getPatientInsuranceBySearchParametersQueryHandler(PatientInsuranceSearchByPatientIdAndInsuranceId obj);

    Flux<PatientInsuranceDTO> getPatientInsuranceByPatientIdQueryHandler(PatientInsuranceAuditLogByPatientNoAndUserIdAndDateQuery obj);

    Mono<Long> getIDByUUID(UUID patientUuid);

    Mono<Object> getPatientInsuranceByPatInsuranceId(Long patInsuranceId);

    Mono<PatientInsuranceDTO> getPatientInsuranceByPatientUUIDAndPayerLevel(PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel);

    Mono<ServiceOutcome> getExixtingPayerLevelsByPatientUUID(Long patientId) throws ExecutionException, InterruptedException;

    Mono<PatientInsuranceDTO> getPatientInsuranceInfoByPatientInsId(Long patientInsId);

    Mono<Long> getPatientIdByPatientInsuranceUUID(UUID patientInsuranceUUID);
}
