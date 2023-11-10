package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface FunctionalAbilitySearchServiceExtended {
    Flux<FunctionalAbilityDTO> getFunctionalAbilityBySearchParameters(FunctionalAbilitySearchByFuncAbIdOrFuncAbCodeOrFuncAbName obj);

    Long getIDByUUID(UUID functionalAbilityUUID);

    Flux<FunctionalAbilityDTO> getAllFunctionalAbility();

    Mono<Object> getAllFunctionalAbilityByUUID(Long functionalAbilityId);
    List<Map<String, Object>> getAllFunctionalAbilityForDropdown() throws ExecutionException, InterruptedException;
}
