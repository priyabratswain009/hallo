package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PickupExchangeItemsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    Mono<PickupExchangeItemsDTO> save(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Updates a pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PickupExchangeItemsDTO> update(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Partially updates a pickupExchangeItems.
     *
     * @param pickupExchangeItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PickupExchangeItemsDTO> partialUpdate(PickupExchangeItemsDTO pickupExchangeItemsDTO);

    /**
     * Get all the pickupExchangeItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PickupExchangeItemsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of pickupExchangeItems available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" pickupExchangeItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PickupExchangeItemsDTO> findOne(Long id);

    /**
     * Delete the "id" pickupExchangeItems.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
