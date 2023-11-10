package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientBucketParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientExistCheckingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientSearchBasicParameterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientDTOSearchServiceExtended {

    Flux<PatientDtoDTO> getPatientBucketDataBySearchParam(PatientBucketParameterDTO patientBucketParameterDTO);
}
