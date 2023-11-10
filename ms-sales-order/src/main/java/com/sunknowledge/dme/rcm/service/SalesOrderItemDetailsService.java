package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails}.
 */
public interface SalesOrderItemDetailsService {
    /**
     * Save a salesOrderItemDetails.
     *
     * @param salesOrderItemDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsDTO> save(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO);

    /**
     * Updates a salesOrderItemDetails.
     *
     * @param salesOrderItemDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsDTO> update(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO);

    /**
     * Partially updates a salesOrderItemDetails.
     *
     * @param salesOrderItemDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsDTO> partialUpdate(SalesOrderItemDetailsDTO salesOrderItemDetailsDTO);

    /**
     * Get all the salesOrderItemDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderItemDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderItemDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderItemDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderItemDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderItemDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
