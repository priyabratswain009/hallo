package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog}.
 */
public interface SalesOrderClinicalDetailsAuditLogService {
    /**
     * Save a salesOrderClinicalDetailsAuditLog.
     *
     * @param salesOrderClinicalDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsAuditLogDTO> save(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO);

    /**
     * Updates a salesOrderClinicalDetailsAuditLog.
     *
     * @param salesOrderClinicalDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsAuditLogDTO> update(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO);

    /**
     * Partially updates a salesOrderClinicalDetailsAuditLog.
     *
     * @param salesOrderClinicalDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SalesOrderClinicalDetailsAuditLogDTO> partialUpdate(SalesOrderClinicalDetailsAuditLogDTO salesOrderClinicalDetailsAuditLogDTO);

    /**
     * Get all the salesOrderClinicalDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SalesOrderClinicalDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of salesOrderClinicalDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" salesOrderClinicalDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SalesOrderClinicalDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderClinicalDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
