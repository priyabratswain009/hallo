package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsVerifSearchByInsVerifIdOrPatientInsId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PatientInsuranceVerificationSearchServiceExtended {

    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationBySearchParameters(PatientInsVerifSearchByInsVerifIdOrPatientInsId obj);

    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByInsuranceIds(List<Long> patientInsuranceIdList);

    Long getIDByUUID(UUID patientInsVerifStatUuid);

    Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationDetail(Long pInVeSid) throws ExecutionException, InterruptedException;

    Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsId(Long patientInsId) throws ExecutionException, InterruptedException;

    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsuranceUUID(Long patientInsuranceId) throws ExecutionException, InterruptedException;

    Mono<PatientInsVerifStatDTO> getLastetPatientInsuranceEligibilityStatusByPatientInsId(Long patientInsId);
}
