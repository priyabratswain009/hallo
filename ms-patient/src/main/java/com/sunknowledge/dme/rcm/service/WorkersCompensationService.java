package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WorkersCompensation}.
 */
public interface WorkersCompensationService {
    /**
     * Save a workersCompensation.
     *
     * @param workersCompensationDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationDTO> save(WorkersCompensationDTO workersCompensationDTO);

    /**
     * Updates a workersCompensation.
     *
     * @param workersCompensationDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationDTO> update(WorkersCompensationDTO workersCompensationDTO);

    /**
     * Partially updates a workersCompensation.
     *
     * @param workersCompensationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationDTO> partialUpdate(WorkersCompensationDTO workersCompensationDTO);

    /**
     * Get all the workersCompensations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<WorkersCompensationDTO> findAll(Pageable pageable);

    /**
     * Returns the number of workersCompensations available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" workersCompensation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<WorkersCompensationDTO> findOne(Long id);

    /**
     * Delete the "id" workersCompensation.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
