package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientClinicalSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.PatientDiagnosisSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.impl.patientsearch.FunctionalAbilitySearchServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationMapper;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientClinicalSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDiagnosisSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class PatientDiagnosisSearchQueryHandlerImpl implements PatientDiagnosisSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisSearchQueryHandlerImpl.class);
    @Autowired
    PatientDiagnosisSearchRepositoryExtended patientDiagnosisSearchRepositoryExtended;

    private final  PatientDiagnosisMapper patientDiagnosisMapper;

    public PatientDiagnosisSearchQueryHandlerImpl(PatientDiagnosisMapper patientDiagnosisMapper) {
        this.patientDiagnosisMapper = patientDiagnosisMapper;
    }

    @Override
    public Flux<PatientDiagnosisDTO> getPatientDiagnosisBySearchParametersQueryHandler(PatientDiagnosisSearchByPatIdPatDiaId obj) {
        try {
            return patientDiagnosisSearchRepositoryExtended.findAll()
                .map(patientDiagnosisMapper::toDto)
                .filter(x ->
                    (
                        ((x.getPatientId() == obj.getPatientID() && obj.getPatientDiagnosisId() == 0) ||
                            (x.getPatientDiagnosisId() == obj.getPatientDiagnosisId() && obj.getPatientID() == 0)) &&
                            x.getStatus().trim().equalsIgnoreCase("active")
                    )
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getIDByUUID(UUID patientDiagnosisUuid) {
        try {
            return patientDiagnosisSearchRepositoryExtended.getIDByUUID(patientDiagnosisUuid).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<PatientDiagnosisDTO> getPatientDiagnosisByPatientId(Long patientId){
        try {
            return patientDiagnosisSearchRepositoryExtended.findAll()
                .map(patientDiagnosisMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == patientId
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getPatientDiagnosisByPatientDiagnosisId(Long patDiagnosisId) {
        try {
            return patientDiagnosisSearchRepositoryExtended.findById(patDiagnosisId)
                .map(patientDiagnosisMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getCurrentPatientDiagnosisByMaxId(Long patientId) {
        try {
            return patientDiagnosisSearchRepositoryExtended.findByMaxIdAndMaxCreatedDate(patientId)
                .map(patientDiagnosisMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
