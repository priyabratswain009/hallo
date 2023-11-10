package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsCob835DetailsDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCob835Details}.
 */
public interface ClaimsCob835DetailsService {
    /**
     * Save a claimsCob835Details.
     *
     * @param claimsCob835DetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835DetailsDTO> save(ClaimsCob835DetailsDTO claimsCob835DetailsDTO);

    /**
     * Updates a claimsCob835Details.
     *
     * @param claimsCob835DetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835DetailsDTO> update(ClaimsCob835DetailsDTO claimsCob835DetailsDTO);

    /**
     * Partially updates a claimsCob835Details.
     *
     * @param claimsCob835DetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ClaimsCob835DetailsDTO> partialUpdate(ClaimsCob835DetailsDTO claimsCob835DetailsDTO);

    /**
     * Get all the claimsCob835Details.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ClaimsCob835DetailsDTO> findAll(Pageable pageable);

    /**
     * Returns the number of claimsCob835Details available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" claimsCob835Details.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ClaimsCob835DetailsDTO> findOne(Long id);

    /**
     * Delete the "id" claimsCob835Details.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
