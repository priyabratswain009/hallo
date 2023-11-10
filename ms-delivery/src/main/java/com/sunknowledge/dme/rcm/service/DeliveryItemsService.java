package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryItems}.
 */
public interface DeliveryItemsService {
    /**
     * Save a deliveryItems.
     *
     * @param deliveryItemsDTO the entity to save.
     * @return the persisted entity.
     */
    DeliveryItemsDTO save(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Updates a deliveryItems.
     *
     * @param deliveryItemsDTO the entity to update.
     * @return the persisted entity.
     */
    DeliveryItemsDTO update(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Partially updates a deliveryItems.
     *
     * @param deliveryItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeliveryItemsDTO> partialUpdate(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Get all the deliveryItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeliveryItemsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deliveryItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeliveryItemsDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryItems.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
