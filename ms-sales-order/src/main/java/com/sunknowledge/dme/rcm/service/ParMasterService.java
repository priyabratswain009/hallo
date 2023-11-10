package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ParMaster}.
 */
public interface ParMasterService {
    /**
     * Save a parMaster.
     *
     * @param parMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ParMasterDTO> save(ParMasterDTO parMasterDTO);

    /**
     * Updates a parMaster.
     *
     * @param parMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ParMasterDTO> update(ParMasterDTO parMasterDTO);

    /**
     * Partially updates a parMaster.
     *
     * @param parMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ParMasterDTO> partialUpdate(ParMasterDTO parMasterDTO);

    /**
     * Get all the parMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ParMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of parMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" parMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ParMasterDTO> findOne(Long id);

    /**
     * Delete the "id" parMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
