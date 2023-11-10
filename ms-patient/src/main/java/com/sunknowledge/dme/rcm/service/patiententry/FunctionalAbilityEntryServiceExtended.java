package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.FunctionalAbilityParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SaveFunctionalAbilityCommand;
import reactor.core.publisher.Mono;

public interface FunctionalAbilityEntryServiceExtended {
    Mono<ResponseDTO> saveFunctionalAbility(SaveFunctionalAbilityCommand obj);
}
