package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterV2DTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import org.json.simple.JSONObject;
import reactor.core.publisher.Mono;

public interface PatientDoctorMappingServiceExtended {
Mono<ResponseDTO> savePatientDoctorMapping(SavePatientDoctorMappingCommand obj, Long countPats);
Mono<ResponseDTO> savePatientDoctorMappingV2(Long countPats, ResponseDTO docJson, SavePatientDoctorMappingCommand docObj, String message);

    Mono<ResponseDTO> savePatientDoctorMapping_V2(SavePatientDoctorMappingCommand savePatientDoctorMappingCommand);
}
