package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrderItems}.
 */
public interface PurchaseOrderItemsService {
    /**
     * Save a purchaseOrderItems.
     *
     * @param purchaseOrderItemsDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderItemsDTO save(PurchaseOrderItemsDTO purchaseOrderItemsDTO);

    /**
     * Updates a purchaseOrderItems.
     *
     * @param purchaseOrderItemsDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderItemsDTO update(PurchaseOrderItemsDTO purchaseOrderItemsDTO);

    /**
     * Partially updates a purchaseOrderItems.
     *
     * @param purchaseOrderItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderItemsDTO> partialUpdate(PurchaseOrderItemsDTO purchaseOrderItemsDTO);

    /**
     * Get all the purchaseOrderItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderItemsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrderItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderItemsDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrderItems.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
