package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DeliveryAbnDataDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DeliveryAbnData}.
 */
public interface DeliveryAbnDataService {
    /**
     * Save a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DeliveryAbnDataDTO> save(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Updates a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DeliveryAbnDataDTO> update(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Partially updates a deliveryAbnData.
     *
     * @param deliveryAbnDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DeliveryAbnDataDTO> partialUpdate(DeliveryAbnDataDTO deliveryAbnDataDTO);

    /**
     * Get all the deliveryAbnData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DeliveryAbnDataDTO> findAll(Pageable pageable);

    /**
     * Returns the number of deliveryAbnData available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" deliveryAbnData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DeliveryAbnDataDTO> findOne(Long id);

    /**
     * Delete the "id" deliveryAbnData.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
