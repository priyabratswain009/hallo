package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    Mono<PickupExchangeDTO> save(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Updates a pickupExchange.
     *
     * @param pickupExchangeDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PickupExchangeDTO> update(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Partially updates a pickupExchange.
     *
     * @param pickupExchangeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PickupExchangeDTO> partialUpdate(PickupExchangeDTO pickupExchangeDTO);

    /**
     * Get all the pickupExchanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PickupExchangeDTO> findAll(Pageable pageable);

    /**
     * Returns the number of pickupExchanges available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" pickupExchange.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PickupExchangeDTO> findOne(Long id);

    /**
     * Delete the "id" pickupExchange.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
