package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientelligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceVerificationUUIDParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceVerificationCommand;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.impl.patiententry.TokenGenerationServiceImpl;
import com.sunknowledge.dme.rcm.service.patiententry.PatientInsuranceVerificationEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientInsuranceVerificationSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientInsuranceVerificationServiceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientInsuranceVerificationResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientInsuranceVerificationResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientInsuranceVerificationServiceExtended")
    PatientInsuranceVerificationEntryServiceExtended patientInsuranceVerificationServiceExtended;
    @Autowired
    PatientInsuranceVerificationSearchServiceExtended patientInsuranceVerificationSearchServiceExtended;
    @Autowired
    PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;

    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    @Autowired
    private TokenGenerationServiceImpl tokenGenerationService;

    /**
     * Save Patient Insurance Verification
     */
    @PatchMapping(value = "savePatientInsuranceVerification", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> savePatientInsuranceVerification(
        @RequestBody @Valid PatientInsuranceVerificationUUIDParameterDTO patientInsuranceVerificationParameterDTO
    ) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        // ----If UUID not required, then pass UUID as null ----------
        Long pInId = 0L;
        if (patientInsuranceVerificationParameterDTO.getPatientInsuranceUuid() != null) {
            pInId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceVerificationParameterDTO.getPatientInsuranceUuid()).toFuture().get();
            pInId = pInId == null ? 0L : pInId;
        }
        Long pInVerId = 0L;
        if (patientInsuranceVerificationParameterDTO.getPatientInsVerifStatUuid() != null) {
            pInVerId = patientInsuranceVerificationSearchServiceExtended.getIDByUUID(patientInsuranceVerificationParameterDTO.getPatientInsVerifStatUuid());
            pInVerId = pInVerId == null ? 0L : pInVerId;
        }
        SavePatientInsuranceVerificationCommand obj = new SavePatientInsuranceVerificationCommand();
        BeanUtils.copyProperties(patientInsuranceVerificationParameterDTO,obj);
        obj.setPatientInsuranceId(pInId);
        obj.setInsuranceVerifId(pInVerId);
        return patientInsuranceVerificationServiceExtended.savePatientInsuranceVerification(obj);
    }

    /*****Old Code Reactive Problem*****/
    /*@PatchMapping(value = "patientElligibility")
    public Mono<ResponseDTO> patientElligibility(
            @RequestParam ("patientId") String patientId
            ) {
        //Id by UUID
        *//*
        @RequestParam ("patientUUID") UUID patientUUID
        Long patientId = 0L;
        if (patientUUID != null) {
            patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUUID);
        }
        System.out.println("===patientUUID===="+patientUUID);*//*
        return patientInsuranceVerificationServiceExtended.checkPatientElligibility(patientId);
    }*/

    @GetMapping(value = "patientElligibility")
    public Mono<ResponseDTO> patientElligibility(
        @RequestParam ("patientInsuranceUUID") UUID patientInsuranceUUID
    ) throws ExecutionException, InterruptedException {

        Long patientId = 0L;
        Long patientInsId = 0L;
        patientInsId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceUUID).toFuture().get();
        if(patientInsId ==null || patientInsId == 0L){
            return Mono.just(new ResponseDTO(false, "Patient Insurance UUID does not Exist.", null));
        }
        patientId = patientInsuranceSearchServiceExtended.getPatientIdByPatientInsuranceUUID(patientInsuranceUUID).toFuture().get();
        PatientSearchByBasicInfoOrAddressOrBranch obj = new PatientSearchByBasicInfoOrAddressOrBranch();
        obj.setPatientID(Long.valueOf(patientId));

        PatientMasterDTO objpatient = patientMasterSearchServiceExtended.getPatientBySearchParameters(obj).collectList().toFuture().get().get(0);
        PatientInsuranceDTO patientInsuranceDTO = patientInsuranceSearchServiceExtended.getPatientInsuranceInfoByPatientInsId(patientInsId).toFuture().get();
        log.info("============objpatient============="+objpatient);
        log.info("===============patientInsuranceDTO==============="+patientInsuranceDTO);
        TokenOutCome routcome = tokenGenerationService.getTokenMono().toFuture().get();
        String accessToken = null;

        if(routcome.getOutcome()){
            accessToken = routcome.getTokenResponseOutput().getAccessToken();
        }
        return patientInsuranceVerificationServiceExtended.checkPatientElligibilityReactive(patientId, objpatient, patientInsuranceDTO, accessToken);
    }
}
