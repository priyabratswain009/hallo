package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SoRentalHelperDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SoRentalHelper}.
 */
public interface SoRentalHelperService {
    /**
     * Save a soRentalHelper.
     *
     * @param soRentalHelperDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SoRentalHelperDTO> save(SoRentalHelperDTO soRentalHelperDTO);

    /**
     * Updates a soRentalHelper.
     *
     * @param soRentalHelperDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SoRentalHelperDTO> update(SoRentalHelperDTO soRentalHelperDTO);

    /**
     * Partially updates a soRentalHelper.
     *
     * @param soRentalHelperDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SoRentalHelperDTO> partialUpdate(SoRentalHelperDTO soRentalHelperDTO);

    /**
     * Get all the soRentalHelpers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SoRentalHelperDTO> findAll(Pageable pageable);

    /**
     * Returns the number of soRentalHelpers available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" soRentalHelper.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SoRentalHelperDTO> findOne(Long id);

    /**
     * Delete the "id" soRentalHelper.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
