package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog}.
 */
public interface PurchaseOrderItemsReceivedAuditLogService {
    /**
     * Save a purchaseOrderItemsReceivedAuditLog.
     *
     * @param purchaseOrderItemsReceivedAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderItemsReceivedAuditLogDTO save(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO);

    /**
     * Updates a purchaseOrderItemsReceivedAuditLog.
     *
     * @param purchaseOrderItemsReceivedAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderItemsReceivedAuditLogDTO update(PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO);

    /**
     * Partially updates a purchaseOrderItemsReceivedAuditLog.
     *
     * @param purchaseOrderItemsReceivedAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderItemsReceivedAuditLogDTO> partialUpdate(
        PurchaseOrderItemsReceivedAuditLogDTO purchaseOrderItemsReceivedAuditLogDTO
    );

    /**
     * Get all the purchaseOrderItemsReceivedAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderItemsReceivedAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrderItemsReceivedAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderItemsReceivedAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderItemsReceivedAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
