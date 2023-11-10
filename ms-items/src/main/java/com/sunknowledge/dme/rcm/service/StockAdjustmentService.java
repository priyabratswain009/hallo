package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StockAdjustment}.
 */
public interface StockAdjustmentService {
    /**
     * Save a stockAdjustment.
     *
     * @param stockAdjustmentDTO the entity to save.
     * @return the persisted entity.
     */
    StockAdjustmentDTO save(StockAdjustmentDTO stockAdjustmentDTO);

    /**
     * Updates a stockAdjustment.
     *
     * @param stockAdjustmentDTO the entity to update.
     * @return the persisted entity.
     */
    StockAdjustmentDTO update(StockAdjustmentDTO stockAdjustmentDTO);

    /**
     * Partially updates a stockAdjustment.
     *
     * @param stockAdjustmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StockAdjustmentDTO> partialUpdate(StockAdjustmentDTO stockAdjustmentDTO);

    /**
     * Get all the stockAdjustments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockAdjustmentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockAdjustment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockAdjustmentDTO> findOne(Long id);

    /**
     * Delete the "id" stockAdjustment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
