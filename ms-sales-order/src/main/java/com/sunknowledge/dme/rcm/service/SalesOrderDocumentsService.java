package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderDocuments}.
 */
public interface SalesOrderDocumentsService {
    /**
     * Save a salesOrderDocuments.
     *
     * @param salesOrderDocumentsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsDTO> save(SalesOrderDocumentsDTO salesOrderDocumentsDTO);

    /**
     * Updates a salesOrderDocuments.
     *
     * @param salesOrderDocumentsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsDTO> update(SalesOrderDocumentsDTO salesOrderDocumentsDTO);

    /**
     * Partially updates a salesOrderDocuments.
     *
     * @param salesOrderDocumentsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsDTO> partialUpdate(SalesOrderDocumentsDTO salesOrderDocumentsDTO);

    /**
     * Get all the salesOrderDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderDocumentsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderDocuments available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderDocuments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderDocumentsDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderDocuments.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
