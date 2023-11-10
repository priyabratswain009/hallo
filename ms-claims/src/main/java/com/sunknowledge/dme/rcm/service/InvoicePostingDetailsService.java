package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    InvoicePostingDetailsDTO save(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Updates a invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    InvoicePostingDetailsDTO update(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Partially updates a invoicePostingDetails.
     *
     * @param invoicePostingDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InvoicePostingDetailsDTO> partialUpdate(InvoicePostingDetailsDTO invoicePostingDetailsDTO);

    /**
     * Get all the invoicePostingDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoicePostingDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" invoicePostingDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoicePostingDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoicePostingDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
