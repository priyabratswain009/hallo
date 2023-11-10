package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog}.
 */
public interface StockAdjustmentAuditLogService {
    /**
     * Save a stockAdjustmentAuditLog.
     *
     * @param stockAdjustmentAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    StockAdjustmentAuditLogDTO save(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO);

    /**
     * Updates a stockAdjustmentAuditLog.
     *
     * @param stockAdjustmentAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    StockAdjustmentAuditLogDTO update(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO);

    /**
     * Partially updates a stockAdjustmentAuditLog.
     *
     * @param stockAdjustmentAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StockAdjustmentAuditLogDTO> partialUpdate(StockAdjustmentAuditLogDTO stockAdjustmentAuditLogDTO);

    /**
     * Get all the stockAdjustmentAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockAdjustmentAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockAdjustmentAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockAdjustmentAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" stockAdjustmentAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
