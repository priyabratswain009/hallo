package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientClinicalSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientClinicalSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("patientClinicalSearchServiceExtended")
public class PatientClinicalSearchServiceExtendedImpl implements PatientClinicalSearchServiceExtended {
    @Autowired
    PatientClinicalSearchQueryHandler patientClinicalSearchQueryHandler;

    @Override
    public Flux<PatientClinicalInformationDTO> getPatientClinicalBySearchParameters(PatientClinicalSearchByPatientId obj) {
        return patientClinicalSearchQueryHandler.getPatientClinicalBySearchParametersQueryHandler(obj);
    }

    @Override
    public Long getIDByUUID(UUID patientClinicalInformationUUID) {
        return patientClinicalSearchQueryHandler.getIDByUUID(patientClinicalInformationUUID);
    }

    @Override
    public Flux<PatientClinicalInformationPatientMasterExtendedDTO> getPatientClinicalByPatientId(Long patientId){
        return patientClinicalSearchQueryHandler.getPatientClinicalByPatientId(patientId);
    }

    @Override
    public Mono<Object> getPatientClinicalByPatientClinicalUuid(Long patClinicalInfoId){
        return patientClinicalSearchQueryHandler.getPatientInsuranceByPatInsuranceId(patClinicalInfoId);
    }

    @Override
    public Mono<ServiceOutcome<PatientClinicalInformationOutputExtendedDTO>>  getCurrentPatientClinicalByMaxId(Long patientId){
        return patientClinicalSearchQueryHandler.getCurrentPatientClinicalByMaxId(patientId);
    }
}
