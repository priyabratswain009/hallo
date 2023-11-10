package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterV2DTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PatientDoctorMappingSearchServiceExtended {
    Mono<PatientDoctorMappingOutputDTO> getPatientDoctorMappingBySearchParameters(PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj);

    Mono<ResponseDTO> getDoctorDetails(String npiId, String Type);

    Mono<Long> getIDByUUID(UUID patientDiagnosisUuid);

    Mono<ResponseDTO> getPatientDoctor(ResponseDTO doctorDeatils, PatientDoctorMappingOutputDTO patientDoctorMapDTOList, Long countPats, Long patientId, String npiId) throws ExecutionException, InterruptedException;

    Flux<PatientDoctorMapPatientMasterExtendedDTO> getPatientDoctorsByPatientId(Long patientId);

    Mono<Object>  getPatientDoctorsByPatientDoctorId(Long patientDoctorMapId);

    Mono<Object> getCurrentPatientDoctorsByMaxId(Long patientId);

    Mono<ResponseDTO> savePatientDoctorMappingV2(PatientDoctorMappingParameterV2DTO patientDoctorMappingParameterV2DTO);

}
