package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived}.
 */
public interface PurchaseOrderItemsReceivedService {
    /**
     * Save a purchaseOrderItemsReceived.
     *
     * @param purchaseOrderItemsReceivedDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderItemsReceivedDTO save(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO);

    /**
     * Updates a purchaseOrderItemsReceived.
     *
     * @param purchaseOrderItemsReceivedDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderItemsReceivedDTO update(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO);

    /**
     * Partially updates a purchaseOrderItemsReceived.
     *
     * @param purchaseOrderItemsReceivedDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderItemsReceivedDTO> partialUpdate(PurchaseOrderItemsReceivedDTO purchaseOrderItemsReceivedDTO);

    /**
     * Get all the purchaseOrderItemsReceiveds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderItemsReceivedDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrderItemsReceived.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderItemsReceivedDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderItemsReceived.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
