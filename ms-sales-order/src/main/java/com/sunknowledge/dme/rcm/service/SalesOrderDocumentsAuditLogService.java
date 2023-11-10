package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderDocumentsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog}.
 */
public interface SalesOrderDocumentsAuditLogService {
    /**
     * Save a salesOrderDocumentsAuditLog.
     *
     * @param salesOrderDocumentsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsAuditLogDTO> save(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO);

    /**
     * Updates a salesOrderDocumentsAuditLog.
     *
     * @param salesOrderDocumentsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsAuditLogDTO> update(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO);

    /**
     * Partially updates a salesOrderDocumentsAuditLog.
     *
     * @param salesOrderDocumentsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderDocumentsAuditLogDTO> partialUpdate(SalesOrderDocumentsAuditLogDTO salesOrderDocumentsAuditLogDTO);

    /**
     * Get all the salesOrderDocumentsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderDocumentsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderDocumentsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderDocumentsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderDocumentsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderDocumentsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
