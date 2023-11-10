package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    InvoiceMasterDetailsDTO save(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Updates a invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    InvoiceMasterDetailsDTO update(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Partially updates a invoiceMasterDetails.
     *
     * @param invoiceMasterDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InvoiceMasterDetailsDTO> partialUpdate(InvoiceMasterDetailsDTO invoiceMasterDetailsDTO);

    /**
     * Get all the invoiceMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceMasterDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" invoiceMasterDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceMasterDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" invoiceMasterDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
