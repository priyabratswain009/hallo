package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails}.
 */
public interface SalesOrderInsuranceDetailsService {
    /**
     * Save a salesOrderInsuranceDetails.
     *
     * @param salesOrderInsuranceDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderInsuranceDetailsDTO> save(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO);

    /**
     * Updates a salesOrderInsuranceDetails.
     *
     * @param salesOrderInsuranceDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderInsuranceDetailsDTO> update(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO);

    /**
     * Partially updates a salesOrderInsuranceDetails.
     *
     * @param salesOrderInsuranceDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderInsuranceDetailsDTO> partialUpdate(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO);

    /**
     * Get all the salesOrderInsuranceDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderInsuranceDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderInsuranceDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderInsuranceDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderInsuranceDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderInsuranceDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
