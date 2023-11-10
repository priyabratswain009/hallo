package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.audittrail.query.PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsVerifSearchByInsVerifIdOrPatientInsId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientInsuranceVerificationSearchQueryHandler {
    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationBySearchParametersQueryHandler(PatientInsVerifSearchByInsVerifIdOrPatientInsId obj);

    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByInsuranceIdsQueryHandler(PatientInsVerifStatAuditLogByPatientNoAndUserIdAndDateQuery obj);

    Long getIDByUUID(UUID patientInsVerifStatUuid);

    Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationDetail(Long pInVeSid);

    Mono<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsId(Long patientInsId);

    Flux<PatientInsVerifStatDTO> getPatientInsuranceVerificationByPatientInsuranceUUID(Long patientInsuranceId);

    Mono<PatientInsVerifStatDTO> getLastetPatientInsuranceEligibilityStatusByPatientInsId(Long patientInsId);
}
