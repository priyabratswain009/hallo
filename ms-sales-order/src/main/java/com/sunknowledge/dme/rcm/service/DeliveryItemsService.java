package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryItemsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    Mono<DeliveryItemsDTO> save(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Updates a deliveryItems.
     *
     * @param deliveryItemsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DeliveryItemsDTO> update(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Partially updates a deliveryItems.
     *
     * @param deliveryItemsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DeliveryItemsDTO> partialUpdate(DeliveryItemsDTO deliveryItemsDTO);

    /**
     * Get all the deliveryItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DeliveryItemsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of deliveryItems available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" deliveryItems.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DeliveryItemsDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryItems.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
