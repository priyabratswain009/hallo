package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog}.
 */
public interface InvoiceMasterDetailsAuditLogService {
    /**
     * Save a invoiceMasterDetailsAuditLog.
     *
     * @param invoiceMasterDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsAuditLogDTO> save(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO);

    /**
     * Updates a invoiceMasterDetailsAuditLog.
     *
     * @param invoiceMasterDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsAuditLogDTO> update(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO);

    /**
     * Partially updates a invoiceMasterDetailsAuditLog.
     *
     * @param invoiceMasterDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsAuditLogDTO> partialUpdate(InvoiceMasterDetailsAuditLogDTO invoiceMasterDetailsAuditLogDTO);

    /**
     * Get all the invoiceMasterDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoiceMasterDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoiceMasterDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoiceMasterDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoiceMasterDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceMasterDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
