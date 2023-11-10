package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderFinancialDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetailsAuditLog}.
 */
public interface SalesOrderFinancialDetailsAuditLogService {
    /**
     * Save a salesOrderFinancialDetailsAuditLog.
     *
     * @param salesOrderFinancialDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsAuditLogDTO> save(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO);

    /**
     * Updates a salesOrderFinancialDetailsAuditLog.
     *
     * @param salesOrderFinancialDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsAuditLogDTO> update(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO);

    /**
     * Partially updates a salesOrderFinancialDetailsAuditLog.
     *
     * @param salesOrderFinancialDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderFinancialDetailsAuditLogDTO> partialUpdate(SalesOrderFinancialDetailsAuditLogDTO salesOrderFinancialDetailsAuditLogDTO);

    /**
     * Get all the salesOrderFinancialDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderFinancialDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderFinancialDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderFinancialDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderFinancialDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderFinancialDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
