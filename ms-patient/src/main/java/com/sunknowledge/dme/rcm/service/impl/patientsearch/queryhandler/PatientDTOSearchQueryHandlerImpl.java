package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.domain.PatientDto;
import com.sunknowledge.dme.rcm.repository.PatientDTOSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.PatientMasterSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientBucketSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import com.sunknowledge.dme.rcm.service.mapper.PatientDtoMapper;
import com.sunknowledge.dme.rcm.service.mapper.PatientMasterMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDTOSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientMasterSearchQueryHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PatientDTOSearchQueryHandlerImpl implements PatientDTOSearchQueryHandler {

    @Autowired
    PatientDTOSearchRepositoryExtended patientDTOSearchRepositoryExtended;

    private final PatientDtoMapper patientDtoMapper;

    public PatientDTOSearchQueryHandlerImpl(PatientDtoMapper patientDtoMapper) {
        this.patientDtoMapper = patientDtoMapper;
    }

    @Override
    public Flux<PatientDtoDTO> getPatientBucketDataBySearchParam(PatientBucketSearchByPatientId queryObj) {
        Flux<PatientDtoDTO> list = patientDTOSearchRepositoryExtended.findAll()
            .map(patientDtoMapper::toDto)
            .filter(x ->
                (
                    x.getPatientId().equals(queryObj.getPatientID())
                        && x.getStatus().trim().equalsIgnoreCase("active")
                )
            );
        Mono<PatientDtoDTO> filteredDTO = list.reduce((max, current) -> current.getPatientDtoId() > max.getPatientDtoId() ? current : max);
        return Flux.from(filteredDTO);
    }

}
