package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.WorkerCompensationParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveWorkerCompensationCommand;
import com.sunknowledge.dme.rcm.service.patiententry.WorkerCompensationEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.WorkerCompensationEntryAggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Transactional
@Service("workerCompensationEntryServiceExtended")
public class WorkerCompensationEntryServiceExtendedImpl implements WorkerCompensationEntryServiceExtended {
    @Autowired
    WorkerCompensationEntryAggregate workerCompensationEntryAggregate;

    @Override
    public Mono<ResponseDTO> saveWorkerCompensation(SaveWorkerCompensationCommand obj) {
        return workerCompensationEntryAggregate.handleSaveWorkerCompensationCommand(obj);
    }
}
