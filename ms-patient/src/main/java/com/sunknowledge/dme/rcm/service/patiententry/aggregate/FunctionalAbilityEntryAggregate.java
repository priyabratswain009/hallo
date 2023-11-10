package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveFunctionalAbilityCommand;
import reactor.core.publisher.Mono;

public interface FunctionalAbilityEntryAggregate {

    Mono<ResponseDTO> handleSaveFunctionalAbilityCommand(SaveFunctionalAbilityCommand saveFunctionalAbilityCommand);
}
