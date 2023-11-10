package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog}.
 */
public interface PurchaseOrderItemsAuditLogService {
    /**
     * Save a purchaseOrderItemsAuditLog.
     *
     * @param purchaseOrderItemsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderItemsAuditLogDTO save(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO);

    /**
     * Updates a purchaseOrderItemsAuditLog.
     *
     * @param purchaseOrderItemsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderItemsAuditLogDTO update(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO);

    /**
     * Partially updates a purchaseOrderItemsAuditLog.
     *
     * @param purchaseOrderItemsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderItemsAuditLogDTO> partialUpdate(PurchaseOrderItemsAuditLogDTO purchaseOrderItemsAuditLogDTO);

    /**
     * Get all the purchaseOrderItemsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderItemsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrderItemsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderItemsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderItemsAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
