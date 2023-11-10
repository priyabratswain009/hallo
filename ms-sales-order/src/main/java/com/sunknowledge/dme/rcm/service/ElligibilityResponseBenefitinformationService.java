package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ElligibilityResponseBenefitinformationDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ElligibilityResponseBenefitinformation}.
 */
public interface ElligibilityResponseBenefitinformationService {
    /**
     * Save a elligibilityResponseBenefitinformation.
     *
     * @param elligibilityResponseBenefitinformationDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseBenefitinformationDTO> save(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    );

    /**
     * Updates a elligibilityResponseBenefitinformation.
     *
     * @param elligibilityResponseBenefitinformationDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseBenefitinformationDTO> update(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    );

    /**
     * Partially updates a elligibilityResponseBenefitinformation.
     *
     * @param elligibilityResponseBenefitinformationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ElligibilityResponseBenefitinformationDTO> partialUpdate(
        ElligibilityResponseBenefitinformationDTO elligibilityResponseBenefitinformationDTO
    );

    /**
     * Get all the elligibilityResponseBenefitinformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ElligibilityResponseBenefitinformationDTO> findAll(Pageable pageable);

    /**
     * Returns the number of elligibilityResponseBenefitinformations available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" elligibilityResponseBenefitinformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ElligibilityResponseBenefitinformationDTO> findOne(Long id);

    /**
     * Delete the "id" elligibilityResponseBenefitinformation.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
