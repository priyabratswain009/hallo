package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveWorkerCompensationCommand;
import reactor.core.publisher.Mono;

public interface WorkerCompensationEntryAggregate {

    Mono<ResponseDTO> handleSaveWorkerCompensationCommand(SaveWorkerCompensationCommand saveWorkerCompensationCommand);
}
