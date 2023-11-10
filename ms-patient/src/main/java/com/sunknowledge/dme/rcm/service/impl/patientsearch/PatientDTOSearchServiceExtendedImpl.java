package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientBucketParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientExistCheckingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientSearchBasicParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientBucketSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDTOSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDTOSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientMasterSearchQueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("patientDTOSearchServiceExtendedImpl")
public class PatientDTOSearchServiceExtendedImpl implements PatientDTOSearchServiceExtended {
    @Autowired
    PatientDTOSearchQueryHandler patientDTOSearchQueryHandler;

    @Override
    public Flux<PatientDtoDTO> getPatientBucketDataBySearchParam(PatientBucketParameterDTO patientBucketParameterDTO) {
        PatientBucketSearchByPatientId queryObj = new PatientBucketSearchByPatientId();
        BeanUtils.copyProperties(patientBucketParameterDTO, queryObj);
        return patientDTOSearchQueryHandler.getPatientBucketDataBySearchParam(queryObj);
    }
}
