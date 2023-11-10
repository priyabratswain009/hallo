package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog}.
 */
public interface InvoicePostingDetailsAuditLogService {
    /**
     * Save a invoicePostingDetailsAuditLog.
     *
     * @param invoicePostingDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsAuditLogDTO> save(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO);

    /**
     * Updates a invoicePostingDetailsAuditLog.
     *
     * @param invoicePostingDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsAuditLogDTO> update(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO);

    /**
     * Partially updates a invoicePostingDetailsAuditLog.
     *
     * @param invoicePostingDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsAuditLogDTO> partialUpdate(InvoicePostingDetailsAuditLogDTO invoicePostingDetailsAuditLogDTO);

    /**
     * Get all the invoicePostingDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoicePostingDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoicePostingDetailsAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoicePostingDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoicePostingDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" invoicePostingDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
