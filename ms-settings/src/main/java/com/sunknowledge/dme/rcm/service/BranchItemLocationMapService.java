package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchItemLocationMap}.
 */
public interface BranchItemLocationMapService {
    /**
     * Save a branchItemLocationMap.
     *
     * @param branchItemLocationMapDTO the entity to save.
     * @return the persisted entity.
     */
    BranchItemLocationMapDTO save(BranchItemLocationMapDTO branchItemLocationMapDTO);

    /**
     * Updates a branchItemLocationMap.
     *
     * @param branchItemLocationMapDTO the entity to update.
     * @return the persisted entity.
     */
    BranchItemLocationMapDTO update(BranchItemLocationMapDTO branchItemLocationMapDTO);

    /**
     * Partially updates a branchItemLocationMap.
     *
     * @param branchItemLocationMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchItemLocationMapDTO> partialUpdate(BranchItemLocationMapDTO branchItemLocationMapDTO);

    /**
     * Get all the branchItemLocationMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchItemLocationMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchItemLocationMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchItemLocationMapDTO> findOne(Long id);

    /**
     * Delete the "id" branchItemLocationMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
