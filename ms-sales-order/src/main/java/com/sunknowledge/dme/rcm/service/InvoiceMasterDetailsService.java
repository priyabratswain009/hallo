package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InvoiceMasterDetails}.
 */
public interface InvoiceMasterDetailsService {
    /**
     * Save a invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsDTO> save(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Updates a invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsDTO> update(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Partially updates a invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<InvoiceMasterDetailsDTO> partialUpdate(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Get all the invoiceMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<InvoiceMasterDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of invoiceMasterDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" invoiceMasterDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<InvoiceMasterDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceMasterDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
