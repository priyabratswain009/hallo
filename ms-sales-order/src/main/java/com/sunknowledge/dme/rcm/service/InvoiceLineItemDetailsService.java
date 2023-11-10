package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetails}.
 */
public interface InvoiceLineItemDetailsService {
    /**
     * Save a invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsDTO> save(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Updates a invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsDTO> update(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Partially updates a invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoiceLineItemDetailsDTO> partialUpdate(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Get all the invoiceLineItemDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoiceLineItemDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoiceLineItemDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoiceLineItemDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoiceLineItemDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceLineItemDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
