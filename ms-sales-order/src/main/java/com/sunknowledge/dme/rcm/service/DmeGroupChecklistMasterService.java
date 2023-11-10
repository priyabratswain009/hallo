package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DmeGroupChecklistMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DmeGroupChecklistMaster}.
 */
public interface DmeGroupChecklistMasterService {
    /**
     * Save a dmeGroupChecklistMaster.
     *
     * @param dmeGroupChecklistMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DmeGroupChecklistMasterDTO> save(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO);

    /**
     * Updates a dmeGroupChecklistMaster.
     *
     * @param dmeGroupChecklistMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DmeGroupChecklistMasterDTO> update(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO);

    /**
     * Partially updates a dmeGroupChecklistMaster.
     *
     * @param dmeGroupChecklistMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DmeGroupChecklistMasterDTO> partialUpdate(DmeGroupChecklistMasterDTO dmeGroupChecklistMasterDTO);

    /**
     * Get all the dmeGroupChecklistMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DmeGroupChecklistMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of dmeGroupChecklistMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" dmeGroupChecklistMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DmeGroupChecklistMasterDTO> findOne(Long id);

    /**
     * Delete the "id" dmeGroupChecklistMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
