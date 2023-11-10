package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetails}.
 */
public interface PriceDetailsService {
    /**
     * Save a priceDetails.
     *
     * @param priceDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PriceDetailsDTO> save(PriceDetailsDTO priceDetailsDTO);

    /**
     * Updates a priceDetails.
     *
     * @param priceDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PriceDetailsDTO> update(PriceDetailsDTO priceDetailsDTO);

    /**
     * Partially updates a priceDetails.
     *
     * @param priceDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PriceDetailsDTO> partialUpdate(PriceDetailsDTO priceDetailsDTO);

    /**
     * Get all the priceDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PriceDetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of priceDetails available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" priceDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PriceDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" priceDetails.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
