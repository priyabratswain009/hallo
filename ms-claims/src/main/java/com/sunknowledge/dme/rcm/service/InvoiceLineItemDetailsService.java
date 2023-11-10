package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    InvoiceLineItemDetailsDTO save(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Updates a invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    InvoiceLineItemDetailsDTO update(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Partially updates a invoiceLineItemDetails.
     *
     * @param invoiceLineItemDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InvoiceLineItemDetailsDTO> partialUpdate(InvoiceLineItemDetailsDTO invoiceLineItemDetailsDTO);

    /**
     * Get all the invoiceLineItemDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceLineItemDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" invoiceLineItemDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceLineItemDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceLineItemDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
