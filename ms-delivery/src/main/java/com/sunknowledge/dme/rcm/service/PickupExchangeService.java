package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PickupExchange}.
 */
public interface PickupExchangeService {
    /**
     * Save a pickupExchange.
     *
     * @param pickupExchangeDTO the entity to save.
     * @return the persisted entity.
     */
    PickupExchangeDTO save(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Updates a pickupExchange.
     *
     * @param pickupExchangeDTO the entity to update.
     * @return the persisted entity.
     */
    PickupExchangeDTO update(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Partially updates a pickupExchange.
     *
     * @param pickupExchangeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PickupExchangeDTO> partialUpdate(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Get all the pickupExchanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PickupExchangeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" pickupExchange.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PickupExchangeDTO> findOne(Long id);

    /**
     * Delete the "id" pickupExchange.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
