package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderItemDetailsAuditLog}.
 */
public interface SalesOrderItemDetailsAuditLogService {
    /**
     * Save a salesOrderItemDetailsAuditLog.
     *
     * @param salesOrderItemDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsAuditLogDTO> save(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO);

    /**
     * Updates a salesOrderItemDetailsAuditLog.
     *
     * @param salesOrderItemDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsAuditLogDTO> update(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO);

    /**
     * Partially updates a salesOrderItemDetailsAuditLog.
     *
     * @param salesOrderItemDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderItemDetailsAuditLogDTO> partialUpdate(SalesOrderItemDetailsAuditLogDTO salesOrderItemDetailsAuditLogDTO);

    /**
     * Get all the salesOrderItemDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderItemDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderItemDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderItemDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderItemDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderItemDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
