package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    BenefitCoverageResponseDTO save(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Updates a benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the entity to update.
     * @return the persisted entity.
     */
    BenefitCoverageResponseDTO update(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Partially updates a benefitCoverageResponse.
     *
     * @param benefitCoverageResponseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BenefitCoverageResponseDTO> partialUpdate(BenefitCoverageResponseDTO benefitCoverageResponseDTO);

    /**
     * Get all the benefitCoverageResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BenefitCoverageResponseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" benefitCoverageResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BenefitCoverageResponseDTO> findOne(Long id);

    /**
     * Delete the "id" benefitCoverageResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
