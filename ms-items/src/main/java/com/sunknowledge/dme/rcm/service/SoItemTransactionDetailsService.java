package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails}.
 */
public interface SoItemTransactionDetailsService {
    /**
     * Save a soItemTransactionDetails.
     *
     * @param soItemTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    SoItemTransactionDetailsDTO save(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO);

    /**
     * Updates a soItemTransactionDetails.
     *
     * @param soItemTransactionDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    SoItemTransactionDetailsDTO update(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO);

    /**
     * Partially updates a soItemTransactionDetails.
     *
     * @param soItemTransactionDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SoItemTransactionDetailsDTO> partialUpdate(SoItemTransactionDetailsDTO soItemTransactionDetailsDTO);

    /**
     * Get all the soItemTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SoItemTransactionDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" soItemTransactionDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoItemTransactionDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" soItemTransactionDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
