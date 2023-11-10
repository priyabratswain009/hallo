package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    BenefitCoverageRequestDTO save(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Updates a benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the entity to update.
     * @return the persisted entity.
     */
    BenefitCoverageRequestDTO update(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Partially updates a benefitCoverageRequest.
     *
     * @param benefitCoverageRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BenefitCoverageRequestDTO> partialUpdate(BenefitCoverageRequestDTO benefitCoverageRequestDTO);

    /**
     * Get all the benefitCoverageRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BenefitCoverageRequestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" benefitCoverageRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BenefitCoverageRequestDTO> findOne(Long id);

    /**
     * Delete the "id" benefitCoverageRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
