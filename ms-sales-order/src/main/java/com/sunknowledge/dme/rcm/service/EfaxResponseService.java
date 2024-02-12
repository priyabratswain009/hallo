package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.EfaxResponseDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.EfaxResponse}.
 */
public interface EfaxResponseService {
    /**
     * Save a efaxResponse.
     *
     * @param efaxResponseDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<EfaxResponseDTO> save(EfaxResponseDTO efaxResponseDTO);

    /**
     * Updates a efaxResponse.
     *
     * @param efaxResponseDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<EfaxResponseDTO> update(EfaxResponseDTO efaxResponseDTO);

    /**
     * Partially updates a efaxResponse.
     *
     * @param efaxResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<EfaxResponseDTO> partialUpdate(EfaxResponseDTO efaxResponseDTO);

    /**
     * Get all the efaxResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<EfaxResponseDTO> findAll(Pageable pageable);

    /**
     * Returns the number of efaxResponses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" efaxResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<EfaxResponseDTO> findOne(Long id);

    /**
     * Delete the "id" efaxResponse.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
