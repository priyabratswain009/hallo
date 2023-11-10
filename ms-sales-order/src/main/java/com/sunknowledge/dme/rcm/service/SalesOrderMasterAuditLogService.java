package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderMasterAuditLog}.
 */
public interface SalesOrderMasterAuditLogService {
    /**
     * Save a salesOrderMasterAuditLog.
     *
     * @param salesOrderMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterAuditLogDTO> save(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO);

    /**
     * Updates a salesOrderMasterAuditLog.
     *
     * @param salesOrderMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterAuditLogDTO> update(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO);

    /**
     * Partially updates a salesOrderMasterAuditLog.
     *
     * @param salesOrderMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderMasterAuditLogDTO> partialUpdate(SalesOrderMasterAuditLogDTO salesOrderMasterAuditLogDTO);

    /**
     * Get all the salesOrderMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderMasterAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
