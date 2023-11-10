package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.repository.PatientClinicalSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.mapper.PatientClinicalInformationMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientClinicalSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PatientClinicalSearchQueryHandlerImpl implements PatientClinicalSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(PatientClinicalSearchQueryHandlerImpl.class);
    @Autowired
    PatientClinicalSearchRepositoryExtended patientClinicalSearchRepositoryExtended;

    private final PatientClinicalInformationMapper patientClinicalInformationMapper;

    public PatientClinicalSearchQueryHandlerImpl(PatientClinicalInformationMapper patientClinicalInformationMapper) {
        this.patientClinicalInformationMapper = patientClinicalInformationMapper;
    }

    @Override
    public Flux<PatientClinicalInformationDTO> getPatientClinicalBySearchParametersQueryHandler(PatientClinicalSearchByPatientId obj) {
        try {
            return patientClinicalSearchRepositoryExtended.findAll()
                .map(patientClinicalInformationMapper::toDto)
                .filter(x ->
                    (
                        ((x.getPatientId().equals(obj.getPatientID()) && obj.getPatientClinicalInformationId() == 0) ||
                            (x.getPatientClinicalInformationId().equals(obj.getPatientClinicalInformationId()) && obj.getPatientID() == 0)) &&
                            x.getStatus().trim().equalsIgnoreCase("active")
                    )
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getIDByUUID(UUID patientClinicalInformationUUID) {
        System.out.println("calling ");
        try {
            return patientClinicalSearchRepositoryExtended.getIDByUUID(patientClinicalInformationUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<PatientClinicalInformationPatientMasterExtendedDTO> getPatientClinicalByPatientId(Long patientId) {
        return patientClinicalSearchRepositoryExtended.getPatientClinicalByPatientId(patientId);
    }

    /*@Override
    public Flux<PatientClinicalInformationDTO> getPatientClinicalByPatientId(Long patientId){
        try {
            return patientClinicalSearchRepositoryExtended.findAll()
                .map(patientClinicalInformationMapper::toDto)
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
    }*/

    @Override
    public Mono<Object> getPatientInsuranceByPatInsuranceId(Long patInsuranceId) {
        try {
            return patientClinicalSearchRepositoryExtended.findById(patInsuranceId)
                .map(patientClinicalInformationMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<ServiceOutcome<PatientClinicalInformationOutputExtendedDTO>> getCurrentPatientClinicalByMaxId(Long patientId){
        try {
            PatientClinicalInformationOutputExtendedDTO responseObj = patientClinicalSearchRepositoryExtended.findByMaxIdAndMaxCreatedDate(patientId).toFuture().get();
            if(responseObj!=null && responseObj.getPatientClinicalInformationId()!= null){
                return Mono.justOrEmpty(new ServiceOutcome<>(responseObj, true, "Data Successfully Fetched."));
            }else{
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "No Data Available."));
            }

        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
