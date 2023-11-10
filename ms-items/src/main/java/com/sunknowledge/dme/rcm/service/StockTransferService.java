package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StockTransferDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StockTransfer}.
 */
public interface StockTransferService {
    /**
     * Save a stockTransfer.
     *
     * @param stockTransferDTO the entity to save.
     * @return the persisted entity.
     */
    StockTransferDTO save(StockTransferDTO stockTransferDTO);

    /**
     * Updates a stockTransfer.
     *
     * @param stockTransferDTO the entity to update.
     * @return the persisted entity.
     */
    StockTransferDTO update(StockTransferDTO stockTransferDTO);

    /**
     * Partially updates a stockTransfer.
     *
     * @param stockTransferDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StockTransferDTO> partialUpdate(StockTransferDTO stockTransferDTO);

    /**
     * Get all the stockTransfers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockTransferDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockTransfer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockTransferDTO> findOne(Long id);

    /**
     * Delete the "id" stockTransfer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
