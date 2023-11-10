package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDiagnosisSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientDiagnosisSearchResourceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientDiagnosisSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDiagnosisSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientDiagnosisSearchServiceExtended")
    PatientDiagnosisSearchServiceExtended patientDisgnosisSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient Diagnosis Information By Search Parameters
     */
    @GetMapping("getPatientDiagnosisBySearchParameters")
    public Mono<ResponseDTO<Object>> getPatientDiagnosisBySearchParameters(
        @ModelAttribute PatientDiagnosisSearchParameterDTO patientDiagnosisSearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long id = 0L;
            if (patientDiagnosisSearchParameterDTO.getPatientDiagnosisUuid() != null) {
                id = patientDisgnosisSearchServiceExtended.getIDByUUID(patientDiagnosisSearchParameterDTO.getPatientDiagnosisUuid());
            }
            PatientDiagnosisSearchByPatIdPatDiaId obj = new PatientDiagnosisSearchByPatIdPatDiaId();
            obj.setPatientDiagnosisId(id);
            obj.setPatientID(patientDiagnosisSearchParameterDTO.getPatientId());
            List<PatientDiagnosisDTO> list = patientDisgnosisSearchServiceExtended.getPatientDiagnosisBySearchParameters(obj)
                .collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<Object>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "patientDiagnosisId";
                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", jarr.size()>0?jarr.get(0):null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientDiagnosisByPatientUuid")
    public Mono<ResponseDTO> getPatientDiagnosisByPatientUuid(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            List<PatientDiagnosisDTO> list = patientDisgnosisSearchServiceExtended.getPatientDiagnosisByPatientId(patientId).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientDiagnosisDTO>(false, "Data Not Available.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientClinicalInformationId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", list.size() > 0 ? list : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    //getPatientDiagnosisByPatientDiagnosisUuid
    @GetMapping("getPatientDiagnosisByPatientDiagnosisUuid")
    public Mono<ServiceOutcome<PatientDiagnosisDTO>> getPatientDiagnosisByPatientDiagnosisUuid(
        @RequestParam("patientDiagnosisUuid") UUID patientDiagnosisUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patDiagnosisId = 0L;
            if (patientDiagnosisUuid != null) {
                patDiagnosisId = patientDisgnosisSearchServiceExtended.getIDByUUID(patientDiagnosisUuid);
            }

            PatientDiagnosisDTO patientDiagnosisDTO = (PatientDiagnosisDTO) patientDisgnosisSearchServiceExtended.getPatientDiagnosisByPatientDiagnosisId(patDiagnosisId).toFuture().get();
            if (patientDiagnosisDTO == null || patientDiagnosisDTO.getPatientDiagnosisId() == null) {
                return Mono.just(new ServiceOutcome<PatientDiagnosisDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientDiagnosisDTO>(patientDiagnosisDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getCurrentPatientDiagnosisByMaxId")
    public Mono<ServiceOutcome<PatientDiagnosisDTO>> getCurrentPatientDiagnosisByMaxId(@RequestParam("patientUuid") UUID patientUuid) {
        try {
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            PatientDiagnosisDTO patientDiagnosisDTO = (PatientDiagnosisDTO) patientDisgnosisSearchServiceExtended.getCurrentPatientDiagnosisByMaxId(patientId).toFuture().get();
            if (patientDiagnosisDTO == null || patientDiagnosisDTO.getPatientDiagnosisId() == null) {
                return Mono.just(new ServiceOutcome<PatientDiagnosisDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientDiagnosisDTO>(patientDiagnosisDTO, true, "Data Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
