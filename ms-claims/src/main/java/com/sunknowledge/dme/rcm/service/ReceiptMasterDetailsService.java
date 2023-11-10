package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ReceiptMasterDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails}.
 */
public interface ReceiptMasterDetailsService {
    /**
     * Save a receiptMasterDetails.
     *
     * @param receiptMasterDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    ReceiptMasterDetailsDTO save(ReceiptMasterDetailsDTO receiptMasterDetailsDTO);

    /**
     * Updates a receiptMasterDetails.
     *
     * @param receiptMasterDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    ReceiptMasterDetailsDTO update(ReceiptMasterDetailsDTO receiptMasterDetailsDTO);

    /**
     * Partially updates a receiptMasterDetails.
     *
     * @param receiptMasterDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReceiptMasterDetailsDTO> partialUpdate(ReceiptMasterDetailsDTO receiptMasterDetailsDTO);

    /**
     * Get all the receiptMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReceiptMasterDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" receiptMasterDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReceiptMasterDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" receiptMasterDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
