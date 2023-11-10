package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse}.
 */
public interface BenefitCoverageResponseService {
    /**
     * Save a benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageResponseDTO> save(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Updates a benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageResponseDTO> update(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Partially updates a benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageResponseDTO> partialUpdate(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Get all the benefitCoverageResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<BenefitCoverageResponseDTO> findAll(Pageable pageable);

    /**
     * Returns the number of benefitCoverageResponses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" benefitCoverageResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<BenefitCoverageResponseDTO> findOne(Long id);

    /**
     * Delete the "id" benefitCoverageResponse.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
