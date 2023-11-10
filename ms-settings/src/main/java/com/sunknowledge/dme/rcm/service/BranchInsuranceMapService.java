package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchInsuranceMap}.
 */
public interface BranchInsuranceMapService {
    /**
     * Save a branchInsuranceMap.
     *
     * @param branchInsuranceMapDTO the entity to save.
     * @return the persisted entity.
     */
    BranchInsuranceMapDTO save(BranchInsuranceMapDTO branchInsuranceMapDTO);

    /**
     * Updates a branchInsuranceMap.
     *
     * @param branchInsuranceMapDTO the entity to update.
     * @return the persisted entity.
     */
    BranchInsuranceMapDTO update(BranchInsuranceMapDTO branchInsuranceMapDTO);

    /**
     * Partially updates a branchInsuranceMap.
     *
     * @param branchInsuranceMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchInsuranceMapDTO> partialUpdate(BranchInsuranceMapDTO branchInsuranceMapDTO);

    /**
     * Get all the branchInsuranceMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchInsuranceMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchInsuranceMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchInsuranceMapDTO> findOne(Long id);

    /**
     * Delete the "id" branchInsuranceMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
