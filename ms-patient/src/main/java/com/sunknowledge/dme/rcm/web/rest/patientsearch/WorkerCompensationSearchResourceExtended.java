package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.WorkerCompensationSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.WorkerCompensationSearchServiceExtended;
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
 * REST controller for WorkerCompensationSearchResourceExtended.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class WorkerCompensationSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(WorkerCompensationSearchResourceExtended.class);
    @Autowired
    @Qualifier("workerCompensationSearchServiceExtended")
    WorkerCompensationSearchServiceExtended workerCompensationSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Get Worker Compensation Search Service Extended By Search Parameters
     */
    @GetMapping("getWorkerCompensationBySearchParameters")
    public Mono<ResponseDTO> getWorkerCompensationBySearchParameters(
        @ModelAttribute WorkerCompensationSearchParameterDTO workersCompensationSearchParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long wCid = 0L;
            if (workersCompensationSearchParameterDTO.getWorkersCompensationUuid() != null) {
                wCid = workerCompensationSearchServiceExtended.getIDByUUID(workersCompensationSearchParameterDTO.getWorkersCompensationUuid());
            }

            Long pMasid = 0L;
            if (workersCompensationSearchParameterDTO.getPatientMasterUuid() != null) {
                pMasid = patientMasterSearchServiceExtended.getIDByUUID(workersCompensationSearchParameterDTO.getPatientMasterUuid()).toFuture().get();
            }

            WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj = new WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer();
            obj.setWorkersCompensationId(wCid);
            obj.setPatientId(pMasid);
            List<WorkersCompensationDTO> list = workerCompensationSearchServiceExtended.getWorkerCompensationBySearchParameters(obj).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<WorkersCompensationDTO>(false, "Data Not Found.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
                String keyToRemove = "workersCompensationId";
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

    @GetMapping("getWorkerCompensationByPatientUuid")
    public Mono<ResponseDTO> getWorkerCompensationByPatientUuid(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            List<WorkersCompensationDTO> list = workerCompensationSearchServiceExtended.getWorkerCompensationByPatientId(patientId).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<WorkersCompensationDTO>(false, "Data Not Available.", null));
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
    @GetMapping("getWorkerCompensationByWorkerCompensationUuid")
    public Mono<ServiceOutcome<WorkersCompensationDTO>> getWorkerCompensationByWorkerCompensationUuid(
        @RequestParam("workersCompensationUuid") UUID workersCompensationUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long workersCompensationId = 0L;
            if (workersCompensationUuid != null) {
                workersCompensationId = workerCompensationSearchServiceExtended.getIDByUUID(workersCompensationUuid);
            }

            WorkersCompensationDTO workersCompensationDTO = (WorkersCompensationDTO) workerCompensationSearchServiceExtended.getWorkerCompensationByWorkerCompensationId(workersCompensationId).toFuture().get();
            if (workersCompensationDTO == null || workersCompensationDTO.getWorkersCompensationId() == null) {
                return Mono.just(new ServiceOutcome<WorkersCompensationDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<WorkersCompensationDTO>(workersCompensationDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getCurrentWorkerCompensationByMaxId")
    public Mono<ServiceOutcome<WorkersCompensationDTO>> getCurrentWorkerCompensationByMaxId(@RequestParam("patientUuid") UUID patientUuid) {
        try {
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            WorkersCompensationDTO workersCompensationDTO = (WorkersCompensationDTO) workerCompensationSearchServiceExtended.getCurrentWorkerCompensationByMaxId(patientId).toFuture().get();
            if (workersCompensationDTO == null || workersCompensationDTO.getWorkersCompensationId() == null) {
                return Mono.just(new ServiceOutcome<WorkersCompensationDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<WorkersCompensationDTO>(workersCompensationDTO, true, "Successfully Fetched."));
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
