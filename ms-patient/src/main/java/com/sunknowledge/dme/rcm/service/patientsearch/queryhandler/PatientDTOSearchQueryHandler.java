package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientBucketSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PatientDTOSearchQueryHandler {

    Flux<PatientDtoDTO> getPatientBucketDataBySearchParam(PatientBucketSearchByPatientId queryObj);
}
