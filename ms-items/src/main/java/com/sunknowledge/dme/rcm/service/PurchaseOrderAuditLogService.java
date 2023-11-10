package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog}.
 */
public interface PurchaseOrderAuditLogService {
    /**
     * Save a purchaseOrderAuditLog.
     *
     * @param purchaseOrderAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderAuditLogDTO save(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO);

    /**
     * Updates a purchaseOrderAuditLog.
     *
     * @param purchaseOrderAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderAuditLogDTO update(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO);

    /**
     * Partially updates a purchaseOrderAuditLog.
     *
     * @param purchaseOrderAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderAuditLogDTO> partialUpdate(PurchaseOrderAuditLogDTO purchaseOrderAuditLogDTO);

    /**
     * Get all the purchaseOrderAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrderAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
