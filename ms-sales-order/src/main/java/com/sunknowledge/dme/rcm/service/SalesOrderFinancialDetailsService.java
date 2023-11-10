package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails}.
 */
public interface SalesOrderFinancialDetailsService {
    /**
     * Save a salesOrderFinancialDetails.
     *
     * @param salesOrderFinancialDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsDTO> save(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO);

    /**
     * Updates a salesOrderFinancialDetails.
     *
     * @param salesOrderFinancialDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsDTO> update(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO);

    /**
     * Partially updates a salesOrderFinancialDetails.
     *
     * @param salesOrderFinancialDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsDTO> partialUpdate(SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO);

    /**
     * Get all the salesOrderFinancialDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderFinancialDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderFinancialDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderFinancialDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderFinancialDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderFinancialDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
