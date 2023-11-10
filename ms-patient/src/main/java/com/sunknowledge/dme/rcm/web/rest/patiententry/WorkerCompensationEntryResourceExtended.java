package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.WorkerCompensationParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveWorkerCompensationCommand;
import com.sunknowledge.dme.rcm.service.patiententry.WorkerCompensationEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.WorkerCompensationSearchServiceExtended;
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
 * REST controller for WorkerCompensationEntryResource.
 */
//@Validated
@RestController
@RequestMapping("/api")
public class WorkerCompensationEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(WorkerCompensationEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("workerCompensationEntryServiceExtended")
    WorkerCompensationEntryServiceExtended workerCompensationEntryServiceExtended;
    @Autowired
    WorkerCompensationSearchServiceExtended workerCompensationSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    /**
     * Save Worker Compensation
     */
    @PatchMapping(value = "saveWorkerCompensation", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveWorkerCompensation(
        @RequestBody @Valid WorkerCompensationParameterDTO workerCompensationParameterDTO
    ) throws ExecutionException, InterruptedException {
        SaveWorkerCompensationCommand obj =
            new SaveWorkerCompensationCommand();
        BeanUtils.copyProperties(workerCompensationParameterDTO, obj);

        //----- Implementing UUID_To_ID Bridge Method ----------
        // ----If UUID not required, then pass UUID as null ----------
        Long id = 0L;
        if (workerCompensationParameterDTO.getWorkersCompensationUuid() != null) {
            id = workerCompensationSearchServiceExtended.getIDByUUID(workerCompensationParameterDTO.getWorkersCompensationUuid());
            id = id == null ? 0L : id;
        }
        obj.setWorkersCompensationId(id);

        Long pId = 0L;
        if (workerCompensationParameterDTO.getPatientMasterUUID() != null) {
            pId = patientMasterSearchServiceExtended.getIDByUUID(workerCompensationParameterDTO.getPatientMasterUUID()).toFuture().get();
            pId = pId == null ? 0L : pId;
        }
        obj.setPatientId(pId);
        return workerCompensationEntryServiceExtended.saveWorkerCompensation(obj);
    }
}
