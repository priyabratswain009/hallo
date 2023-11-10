package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FunctionalAbilitySearchQueryHandler {
    Flux<FunctionalAbilityDTO> getFunctionalAbilityBySearchParameters(FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName obj);

    Long getIDByUUID(UUID functionalAbilityUUID);

    Flux<FunctionalAbilityDTO> getAllFunctionalAbility();

    Mono<Object> getAllFunctionalAbilityByUUID(Long functionalAbilityId);
}
