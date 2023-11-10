package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest}.
 */
public interface BenefitCoverageRequestService {
    /**
     * Save a benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageRequestDTO> save(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Updates a benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageRequestDTO> update(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Partially updates a benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<BenefitCoverageRequestDTO> partialUpdate(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Get all the benefitCoverageRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<BenefitCoverageRequestDTO> findAll(Pageable pageable);

    /**
     * Returns the number of benefitCoverageRequests available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" benefitCoverageRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<BenefitCoverageRequestDTO> findOne(Long id);

    /**
     * Delete the "id" benefitCoverageRequest.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
