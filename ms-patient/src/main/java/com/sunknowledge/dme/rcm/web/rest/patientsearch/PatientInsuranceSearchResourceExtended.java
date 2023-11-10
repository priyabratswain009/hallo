package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientInsuranceAndInsEligibilityStatusOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceByPatientUUIDAndPayerLevel;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientInsuranceSearchByPatientIdAndInsuranceId;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class PatientInsuranceSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientInsuranceSearchResourceExtended.class);
    @Autowired
    @Qualifier("patientInsuranceSearchServiceExtendedImpl")
    PatientInsuranceSearchServiceExtended patientInsuranceSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    @Autowired
    PatientInsuranceVerificationSearchServiceExtended patientInsuranceVerificationSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Patient Insurance By Search Parameters
     */
    @GetMapping("getPatientInsuranceByPatientUuid")
    public Mono<ResponseDTO> getPatientInsuranceBySearchParameters(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long id = 0L;
            if (patientUuid != null) {
                id = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            PatientInsuranceSearchByPatientIdAndInsuranceId obj = new PatientInsuranceSearchByPatientIdAndInsuranceId();
            obj.setPatientID(id);

            List<PatientInsuranceDTO> list = patientInsuranceSearchServiceExtended.getPatientInsuranceBySearchParameters(obj).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientInsuranceDTO>(false, "Data Not Available.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientInsuranceId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                Collections.sort(list, Comparator.comparing(PatientInsuranceDTO::getPayerLevel));
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

    /**
     * Get Patient Insurance By Search Parameters
     */
    @GetMapping("getPatientInsuranceByPatientInsuranceUuid")
    public Mono<ServiceOutcome<PatientInsuranceDTO>> getPatientInsuranceByPatientInsuranceUuid(
        @RequestParam("patientInsuranceUuid") UUID patientInsuranceUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patInsId = 0L;
            if (patientInsuranceUuid != null) {
                patInsId = patientInsuranceSearchServiceExtended.getIDByUUID(patientInsuranceUuid).toFuture().get();
            }

            PatientInsuranceSearchByPatientIdAndInsuranceId obj = new PatientInsuranceSearchByPatientIdAndInsuranceId();
            obj.setPatInsuranceId(patInsId);

            PatientInsuranceDTO patientInsuranceDTO = (PatientInsuranceDTO) patientInsuranceSearchServiceExtended.getPatientInsuranceByPatInsuranceId(obj).toFuture().get();
            if (patientInsuranceDTO == null || patientInsuranceDTO.getInsuranceId() == null) {
                return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(patientInsuranceDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getPatientInsuranceByPatientUUIDAndPayerLevel")
    public Mono<ServiceOutcome> getPatientInsuranceByPatientUUIDAndPayerLevel(
        @RequestParam("patientUuid") UUID patientUuid,
        @RequestParam("payerLevel") String payerLevel
    ) throws ExecutionException, InterruptedException {
        Long pId = 0L;
        if (patientUuid != null) {
            pId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
        }
        PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel = new PatientInsuranceByPatientUUIDAndPayerLevel();
        patientInsuranceByPatientUUIDAndPayerLevel.setPatientId(pId);
        patientInsuranceByPatientUUIDAndPayerLevel.setPayerLevel(payerLevel);
        PatientInsuranceDTO patientInsuranceDTO = null;

        PatientInsuranceAndInsEligibilityStatusOutputDTO outputObj = new PatientInsuranceAndInsEligibilityStatusOutputDTO();
        ;
        try {
            patientInsuranceDTO = patientInsuranceSearchServiceExtended.getPatientInsuranceByPatientUUIDAndPayerLevel(patientInsuranceByPatientUUIDAndPayerLevel).toFuture().get();
            log.info("==========patientInsuranceId==========" + patientInsuranceDTO.getPatientInsuranceId());
            PatientInsVerifStatDTO patientInsVerifStatDTO = patientInsuranceVerificationSearchServiceExtended.getLastetPatientInsuranceEligibilityStatusByPatientInsId(patientInsuranceDTO.getPatientInsuranceId()).toFuture().get();
            log.info("=======patientInsVerifStatDTO=======" + patientInsVerifStatDTO);
            BeanUtils.copyProperties(patientInsuranceDTO, outputObj);
            if (patientInsVerifStatDTO != null && patientInsVerifStatDTO.getInsuranceVerifId() != null) {
                if (patientInsVerifStatDTO.getElligibilityStatus() != null && !patientInsVerifStatDTO.getElligibilityStatus().trim().equals(""))
                    outputObj.setPatientInsuranceEligibilityStatus(patientInsVerifStatDTO.getElligibilityStatus());
                else
                    outputObj.setPatientInsuranceEligibilityStatus("N");
            } else {
                outputObj.setPatientInsuranceEligibilityStatus("N");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Mono.just(new ServiceOutcome(outputObj.getInsuranceId() != null ? outputObj : null, outputObj.getInsuranceId() != null ? true : false, outputObj.getInsuranceId() != null ? "Fetched Successfully" : "Data Not Found."));
    }

    @GetMapping("/getExixtingPayerLevelsByPatientUUID")
    public Mono<ServiceOutcome> getExixtingPayerLevelsByPatientUUID(
        @RequestParam("patientUuid") UUID patientUuid
    ) throws ExecutionException, InterruptedException {
        Long patientId = 0L;
        if (patientUuid != null) {
            patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            if (patientId != null && patientId > 0) {
                //patientInsuranceDTO =
                return patientInsuranceSearchServiceExtended.getExixtingPayerLevelsByPatientUUID(patientId);
            } else {
                return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(null, false, "Patient Uuid does not exist."));
            }
        } else {
            return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(null, false, "Patient Uuid must be provided."));
        }
    }

    @GetMapping("/getPatientInsuranceByPatientIDAndPayerLevel")
    public Mono<ServiceOutcome<PatientInsuranceDTO>> getPatientInsuranceByPatientIDAndPayerLevel(
        @RequestParam("patientId") Long patientId,
        @RequestParam("payerLevel") String payerLevel
    ) throws ExecutionException, InterruptedException {
        Long pId = patientId;

        PatientInsuranceByPatientUUIDAndPayerLevel patientInsuranceByPatientUUIDAndPayerLevel = new PatientInsuranceByPatientUUIDAndPayerLevel();
        patientInsuranceByPatientUUIDAndPayerLevel.setPatientId(pId);
        patientInsuranceByPatientUUIDAndPayerLevel.setPayerLevel(payerLevel);
        PatientInsuranceDTO patientInsuranceDTO = null;

        try {
            patientInsuranceDTO = patientInsuranceSearchServiceExtended.getPatientInsuranceByPatientUUIDAndPayerLevel(patientInsuranceByPatientUUIDAndPayerLevel).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Mono.just(new ServiceOutcome<PatientInsuranceDTO>(patientInsuranceDTO.getInsuranceId() != null ? patientInsuranceDTO : null, patientInsuranceDTO.getInsuranceId() != null ? true : false, patientInsuranceDTO.getInsuranceId() != null ? "Fetched Successfully" : "Data Not Found."));
    }

}
