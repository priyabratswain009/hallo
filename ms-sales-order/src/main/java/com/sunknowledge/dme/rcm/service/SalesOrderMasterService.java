package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderMaster}.
 */
public interface SalesOrderMasterService {
    /**
     * Save a salesOrderMaster.
     *
     * @param salesOrderMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterDTO> save(SalesOrderMasterDTO salesOrderMasterDTO);

    /**
     * Updates a salesOrderMaster.
     *
     * @param salesOrderMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterDTO> update(SalesOrderMasterDTO salesOrderMasterDTO);

    /**
     * Partially updates a salesOrderMaster.
     *
     * @param salesOrderMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterDTO> partialUpdate(SalesOrderMasterDTO salesOrderMasterDTO);

    /**
     * Get all the salesOrderMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderMasterDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
