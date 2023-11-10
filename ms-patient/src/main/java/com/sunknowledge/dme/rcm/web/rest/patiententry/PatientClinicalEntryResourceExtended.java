package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientClinicalParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientClinicalDetailsCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientClinicalEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.FunctionalAbilitySearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientClinicalSearchServiceExtended;
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
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientClinicalEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientClinicalEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientClinicalEntryServiceExtendedImpl")
    PatientClinicalEntryServiceExtended patientClinicalEntryServiceExtended;
    @Autowired
    PatientClinicalSearchServiceExtended patientClinicalSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    FunctionalAbilitySearchServiceExtended functionalAbilitySearchServiceExtended;

    /**
     * Save Patient Clinical Information
     */
    @PatchMapping(value = "savePatientClinicalDetails", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatientClinical(
        @RequestBody @Valid PatientClinicalParameterDTO patientClinicalParameterDTO
    ) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (patientClinicalParameterDTO.getPatientClinicalInformationUuid() != null) {
            id = patientClinicalSearchServiceExtended.getIDByUUID(patientClinicalParameterDTO.getPatientClinicalInformationUuid());
            id = id == null ? 0L : id;
        }
        Long pMasId = 0L;
        if (patientClinicalParameterDTO.getPatientUUID() != null) {
            pMasId = patientMasterSearchServiceExtended.getIDByUUID(patientClinicalParameterDTO.getPatientUUID()).toFuture().get();
            pMasId = pMasId == null ? 0L : pMasId;
        }
//        Long funAbiId = 0L;
//        if (patientClinicalParameterDTO.getFunctionalAbilityUUID() != null) {
//            funAbiId = functionalAbilitySearchServiceExtended.getIDByUUID(patientClinicalParameterDTO.getFunctionalAbilityUUID());
//            funAbiId = funAbiId == null ? 0L : funAbiId;
//        }
        SavePatientClinicalDetailsCommand obj = new SavePatientClinicalDetailsCommand();
        BeanUtils.copyProperties(patientClinicalParameterDTO, obj);
        obj.setPatientClinicalInformationId(id);
        obj.setPatientId(pMasId);
//        obj.setFunctionalAbilityId(funAbiId);

        return patientClinicalEntryServiceExtended.savePatientClinicalDetails(obj);
    }
}
