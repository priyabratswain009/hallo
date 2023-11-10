package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientMasterEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientMasterEntryResourceExtended.
 */
@Validated
@RestController
@RequestMapping("/api")
public class PatientMasterEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientMasterEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientMasterEntryServiceExtendedImpl")
    PatientMasterEntryServiceExtended patientMasterEntryServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    /**
     * Save Patient Information
     */
    @PatchMapping(value = "savePatient", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatient(
        @RequestBody @Valid PatientMasterParameterDTO patientMasterParameterDTO
    ) throws ExecutionException, InterruptedException {
        SavePatientCommand obj = new SavePatientCommand();
        BeanUtils.copyProperties(patientMasterParameterDTO, obj);
        //----- Implementing UUID_To_ID Bridge Method ----------
        // ----If UUID not required, then pass UUID as null ----------
        Long pId = 0L;
        if (patientMasterParameterDTO.getPatientMasterUUID() != null) {
            pId = patientMasterSearchServiceExtended.getIDByUUID(patientMasterParameterDTO.getPatientMasterUUID()).toFuture().get();
            pId = pId == null ? 0L : pId;
            obj.setPatientMasterUuid(patientMasterParameterDTO.getPatientMasterUUID());
        }
        obj.setPatientId(pId);
        String branchName = null;
        if(patientMasterParameterDTO.getBranchId()!=null){
            branchName = patientMasterSearchServiceExtended.getBranchNameByBranchId(patientMasterParameterDTO.getBranchId()).toFuture().get();
            if(branchName!=null)
                obj.setBranchName(branchName);
        }

        //---- Patient ID Number ----
        if (patientMasterParameterDTO.getPatientMasterUUID() == null) {
            String idNumber = patientMasterSearchServiceExtended.generatePatientIDNumber();
            if (idNumber != null) {
                obj.setPatientIdNumber(idNumber);
            }
        }

        return patientMasterEntryServiceExtended.savePatient(obj);
    }

    /**
     * Generate PatientDTO (PatientBucket) Data
     */
    @PostMapping(value = "generatePatientBucket/{patientUUID}")
    public Mono<ResponseDTO> generatePatientBucket(
        @NotNull(message = "Patient_UUID must be provided.")
        @PathVariable("patientUUID") UUID patientUUID
    ) {
        return patientMasterEntryServiceExtended.generatePatientBucket(patientUUID);
    }
}
