package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsurancePricetableMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsurancePricetableMap}.
 */
public interface InsurancePricetableMapService {
    /**
     * Save a insurancePricetableMap.
     *
     * @param insurancePricetableMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InsurancePricetableMapDTO> save(InsurancePricetableMapDTO insurancePricetableMapDTO);

    /**
     * Updates a insurancePricetableMap.
     *
     * @param insurancePricetableMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InsurancePricetableMapDTO> update(InsurancePricetableMapDTO insurancePricetableMapDTO);

    /**
     * Partially updates a insurancePricetableMap.
     *
     * @param insurancePricetableMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InsurancePricetableMapDTO> partialUpdate(InsurancePricetableMapDTO insurancePricetableMapDTO);

    /**
     * Get all the insurancePricetableMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InsurancePricetableMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of insurancePricetableMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" insurancePricetableMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InsurancePricetableMapDTO> findOne(Long id);

    /**
     * Delete the "id" insurancePricetableMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
