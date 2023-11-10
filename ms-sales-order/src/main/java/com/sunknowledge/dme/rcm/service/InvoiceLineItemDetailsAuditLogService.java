package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog}.
 */
public interface InvoiceLineItemDetailsAuditLogService {
    /**
     * Save a invoiceLineItemDetailsAuditLog.
     *
     * @param invoiceLineItemDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsAuditLogDTO> save(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO);

    /**
     * Updates a invoiceLineItemDetailsAuditLog.
     *
     * @param invoiceLineItemDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsAuditLogDTO> update(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO);

    /**
     * Partially updates a invoiceLineItemDetailsAuditLog.
     *
     * @param invoiceLineItemDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsAuditLogDTO> partialUpdate(InvoiceLineItemDetailsAuditLogDTO invoiceLineItemDetailsAuditLogDTO);

    /**
     * Get all the invoiceLineItemDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoiceLineItemDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoiceLineItemDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoiceLineItemDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoiceLineItemDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceLineItemDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
