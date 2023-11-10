package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.WorkerCompensationParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveWorkerCompensationCommand;
import reactor.core.publisher.Mono;

public interface WorkerCompensationEntryServiceExtended {
    Mono<ResponseDTO> saveWorkerCompensation(SaveWorkerCompensationCommand obj);
}
