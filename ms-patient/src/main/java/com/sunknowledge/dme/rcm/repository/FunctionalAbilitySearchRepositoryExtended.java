package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FunctionalAbilitySearchRepositoryExtended extends FunctionalAbilityRepository{
    @Query(value="select patient.get_t_functional_ability_id_by_uuid(:functionalAbilityUUID)")
    Mono<Long> getIDByUUID(@Param("functionalAbilityUUID") UUID functionalAbilityUUID);
}
