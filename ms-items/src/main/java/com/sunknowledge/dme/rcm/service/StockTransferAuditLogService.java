package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StockTransferAuditLog}.
 */
public interface StockTransferAuditLogService {
    /**
     * Save a stockTransferAuditLog.
     *
     * @param stockTransferAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    StockTransferAuditLogDTO save(StockTransferAuditLogDTO stockTransferAuditLogDTO);

    /**
     * Updates a stockTransferAuditLog.
     *
     * @param stockTransferAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    StockTransferAuditLogDTO update(StockTransferAuditLogDTO stockTransferAuditLogDTO);

    /**
     * Partially updates a stockTransferAuditLog.
     *
     * @param stockTransferAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StockTransferAuditLogDTO> partialUpdate(StockTransferAuditLogDTO stockTransferAuditLogDTO);

    /**
     * Get all the stockTransferAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StockTransferAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockTransferAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StockTransferAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" stockTransferAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
