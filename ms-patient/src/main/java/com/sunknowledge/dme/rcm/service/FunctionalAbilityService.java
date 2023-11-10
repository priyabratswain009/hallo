package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalAbility}.
 */
public interface FunctionalAbilityService {
    /**
     * Save a functionalAbility.
     *
     * @param functionalAbilityDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityDTO> save(FunctionalAbilityDTO functionalAbilityDTO);

    /**
     * Updates a functionalAbility.
     *
     * @param functionalAbilityDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityDTO> update(FunctionalAbilityDTO functionalAbilityDTO);

    /**
     * Partially updates a functionalAbility.
     *
     * @param functionalAbilityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityDTO> partialUpdate(FunctionalAbilityDTO functionalAbilityDTO);

    /**
     * Get all the functionalAbilities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<FunctionalAbilityDTO> findAll(Pageable pageable);

    /**
     * Returns the number of functionalAbilities available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" functionalAbility.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<FunctionalAbilityDTO> findOne(Long id);

    /**
     * Delete the "id" functionalAbility.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
