package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDoctorMappingSearchQueryHandler {
    Mono<PatientDoctorMappingOutputDTO> getPatientDoctorMappingBySearchParameters(PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj);

    Mono<Long> getIDByUUID(UUID patientDoctorMapUuid);

    Flux<PatientDoctorMapPatientMasterExtendedDTO> getPatientDoctorsByPatientId(Long patientId);

    Mono<Object> getPatientDoctorsByPatientDoctorId(Long patientDoctorMapId);
    Mono<Object> getCurrentPatientDoctorsByMaxId(Long patientId);

    Mono<PatientDoctorMappingOutputDTO> getPatientDoctorsByNpi(Long patientId,String npiId);
}
