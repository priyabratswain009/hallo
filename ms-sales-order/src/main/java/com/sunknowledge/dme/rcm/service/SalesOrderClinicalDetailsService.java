package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails}.
 */
public interface SalesOrderClinicalDetailsService {
    /**
     * Save a salesOrderClinicalDetails.
     *
     * @param salesOrderClinicalDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsDTO> save(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO);

    /**
     * Updates a salesOrderClinicalDetails.
     *
     * @param salesOrderClinicalDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsDTO> update(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO);

    /**
     * Partially updates a salesOrderClinicalDetails.
     *
     * @param salesOrderClinicalDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsDTO> partialUpdate(SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO);

    /**
     * Get all the salesOrderClinicalDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderClinicalDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderClinicalDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderClinicalDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderClinicalDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderClinicalDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
