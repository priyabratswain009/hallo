package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientClinicalSearchServiceExtended;
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
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientClinicalSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientClinicalSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientClinicalSearchServiceExtended")
    PatientClinicalSearchServiceExtended patientClinicalSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient Clinical Information By Search Parameters
     */
    @GetMapping("getPatientClinicalBySearchParameters")
    public Mono<ResponseDTO> getPatientClinicalBySearchParameters(
        @ModelAttribute PatientClinicalSearchParameterDTO patientClinicalSearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long id = 0L;
            if (patientClinicalSearchParameterDTO.getPatientClinicalInformationUUID() != null) {
                id = patientClinicalSearchServiceExtended.getIDByUUID(patientClinicalSearchParameterDTO.getPatientClinicalInformationUUID());
            }

            PatientClinicalSearchByPatientId obj = new PatientClinicalSearchByPatientId();
            obj.setPatientID(patientClinicalSearchParameterDTO.getPatientId());
            obj.setPatientClinicalInformationId(id);

            List<PatientClinicalInformationDTO> list = patientClinicalSearchServiceExtended.getPatientClinicalBySearchParameters(obj).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientClinicalInformationDTO>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "patientClinicalInformationId";
                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", jarr.size() > 0 ? jarr.get(0) : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientClinicalByPatientUuid")
    public Mono<ResponseDTO> getPatientClinicalByPatientUuid(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            //PatientClinicalSearchByPatientId obj = new PatientClinicalSearchByPatientId();
            //obj.setPatientID(id);

            List<PatientClinicalInformationPatientMasterExtendedDTO> list = patientClinicalSearchServiceExtended.getPatientClinicalByPatientId(patientId).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientClinicalInformationPatientMasterExtendedDTO>(false, "Data Not Available.", null));
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

    //getPatientClinicalByPatientClinicalUuid
    @GetMapping("getPatientClinicalByPatientClinicalUuid")
    public Mono<ServiceOutcome<PatientClinicalInformationDTO>> getPatientClinicalByPatientClinicalUuid(
        @RequestParam("patientClinicalUuid") UUID patientClinicalUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patClinicalInfoId = 0L;
            if (patientClinicalUuid != null) {
                patClinicalInfoId = patientClinicalSearchServiceExtended.getIDByUUID(patientClinicalUuid);
            }

            //PatientInsuranceSearchByPatientIdAndInsuranceId obj = new PatientInsuranceSearchByPatientIdAndInsuranceId();
            //obj.setPatInsuranceId(patInsId);

            PatientClinicalInformationDTO patientClinicalInformationDTO = (PatientClinicalInformationDTO) patientClinicalSearchServiceExtended.getPatientClinicalByPatientClinicalUuid(patClinicalInfoId).toFuture().get();
            if (patientClinicalInformationDTO == null || patientClinicalInformationDTO.getPatientClinicalInformationId() == null) {
                return Mono.just(new ServiceOutcome<PatientClinicalInformationDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientClinicalInformationDTO>(patientClinicalInformationDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getCurrentPatientClinicalByMaxId")
    public Mono<ServiceOutcome<PatientClinicalInformationOutputExtendedDTO>> getCurrentPatientClinicalByMaxId(@RequestParam("patientUuid") UUID patientUuid) throws ExecutionException, InterruptedException {
        Long patientId = 0L;
        if (patientUuid != null) {
            patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
        }
        return patientClinicalSearchServiceExtended.getCurrentPatientClinicalByMaxId(patientId);
    }
}
