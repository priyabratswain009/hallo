package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDiagnosisParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDiagnosisDetailsCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDiagnosisEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDiagnosisSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientDiagnosisEntryResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientDiagnosisEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientDiagnosisEntryServiceExtended")
    PatientDiagnosisEntryServiceExtended patientDiagnosisEntryServiceExtended;
    @Autowired
    PatientDiagnosisSearchServiceExtended patientDiagnosisSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    /**
     * Save Patient Diagnosis
     */
    @PatchMapping(value = "savePatientDiagnosis", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatientDiagnosis(
        @RequestBody @Valid PatientDiagnosisParameterDTO patientDiagnosisParameterDTO
    ) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (patientDiagnosisParameterDTO.getPatientDiagnosisUuid() != null) {
            id = patientDiagnosisSearchServiceExtended.getIDByUUID(patientDiagnosisParameterDTO.getPatientDiagnosisUuid());
            id = id == null ? 0L : id;
        }
        Long pMasId = 0L;
        if (patientDiagnosisParameterDTO.getPatientUUID() != null) {
            pMasId = patientMasterSearchServiceExtended.getIDByUUID(patientDiagnosisParameterDTO.getPatientUUID()).toFuture().get();
            pMasId = pMasId == null ? 0L : pMasId;
        }
        SavePatientDiagnosisDetailsCommand obj = new SavePatientDiagnosisDetailsCommand();
        BeanUtils.copyProperties(patientDiagnosisParameterDTO,obj);
        obj.setPatientId(pMasId);
        obj.setPatientDiagnosisId(id);
        return patientDiagnosisEntryServiceExtended.savePatientDiagnosis(obj);
    }
}
