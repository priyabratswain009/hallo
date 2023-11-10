package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetails}.
 */
public interface InvoicePostingDetailsService {
    /**
     * Save a invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsDTO> save(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Updates a invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsDTO> update(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Partially updates a invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoicePostingDetailsDTO> partialUpdate(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Get all the invoicePostingDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoicePostingDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoicePostingDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoicePostingDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoicePostingDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoicePostingDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
