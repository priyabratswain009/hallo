package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PickupExchangeItems}.
 */
public interface PickupExchangeItemsService {
    /**
     * Save a pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the entity to save.
     * @return the persisted entity.
     */
    PickupExchangeItemsDTO save(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Updates a pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the entity to update.
     * @return the persisted entity.
     */
    PickupExchangeItemsDTO update(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Partially updates a pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PickupExchangeItemsDTO> partialUpdate(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Get all the pickupExchangeItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PickupExchangeItemsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" pickupExchangeItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PickupExchangeItemsDTO> findOne(Long id);

    /**
     * Delete the "id" pickupExchangeItems.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
