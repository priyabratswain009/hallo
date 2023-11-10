package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SoRecurringPurchaseDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SoRecurringPurchase}.
 */
public interface SoRecurringPurchaseService {
    /**
     * Save a soRecurringPurchase.
     *
     * @param soRecurringPurchaseDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SoRecurringPurchaseDTO> save(SoRecurringPurchaseDTO soRecurringPurchaseDTO);

    /**
     * Updates a soRecurringPurchase.
     *
     * @param soRecurringPurchaseDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SoRecurringPurchaseDTO> update(SoRecurringPurchaseDTO soRecurringPurchaseDTO);

    /**
     * Partially updates a soRecurringPurchase.
     *
     * @param soRecurringPurchaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SoRecurringPurchaseDTO> partialUpdate(SoRecurringPurchaseDTO soRecurringPurchaseDTO);

    /**
     * Get all the soRecurringPurchases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SoRecurringPurchaseDTO> findAll(Pageable pageable);

    /**
     * Returns the number of soRecurringPurchases available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" soRecurringPurchase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SoRecurringPurchaseDTO> findOne(Long id);

    /**
     * Delete the "id" soRecurringPurchase.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
