package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientClinicalSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDiagnosisSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientClinicalSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDiagnosisSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("patientDiagnosisSearchServiceExtended")
public class PatientDiagnosisSearchServiceExtendedImpl implements PatientDiagnosisSearchServiceExtended {
    @Autowired
    PatientDiagnosisSearchQueryHandler patientDiagnosisSearchQueryHandler;

    @Override
    public Flux<PatientDiagnosisDTO> getPatientDiagnosisBySearchParameters(PatientDiagnosisSearchByPatIdPatDiaId obj) {
        return patientDiagnosisSearchQueryHandler.getPatientDiagnosisBySearchParametersQueryHandler(obj);

    }

    @Override
    public Long getIDByUUID(UUID patientDiagnosisUuid) {
        return patientDiagnosisSearchQueryHandler.getIDByUUID(patientDiagnosisUuid);
    }

    @Override
    public Flux<PatientDiagnosisDTO> getPatientDiagnosisByPatientId(Long patientId){
        return patientDiagnosisSearchQueryHandler.getPatientDiagnosisByPatientId(patientId);
    }

    //Mono<Object> getPatientDiagnosisByPatientDiagnosisUuid(Long patDiagnosisId)
    @Override
    public Mono<Object> getPatientDiagnosisByPatientDiagnosisId(Long patDiagnosisId){
        return patientDiagnosisSearchQueryHandler.getPatientDiagnosisByPatientDiagnosisId(patDiagnosisId);
    }

    @Override
    public Mono<Object> getCurrentPatientDiagnosisByMaxId(Long patientId){
        return patientDiagnosisSearchQueryHandler.getCurrentPatientDiagnosisByMaxId(patientId);
    }
}
