package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchUserMap}.
 */
public interface BranchUserMapService {
    /**
     * Save a branchUserMap.
     *
     * @param branchUserMapDTO the entity to save.
     * @return the persisted entity.
     */
    BranchUserMapDTO save(BranchUserMapDTO branchUserMapDTO);

    /**
     * Updates a branchUserMap.
     *
     * @param branchUserMapDTO the entity to update.
     * @return the persisted entity.
     */
    BranchUserMapDTO update(BranchUserMapDTO branchUserMapDTO);

    /**
     * Partially updates a branchUserMap.
     *
     * @param branchUserMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchUserMapDTO> partialUpdate(BranchUserMapDTO branchUserMapDTO);

    /**
     * Get all the branchUserMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchUserMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchUserMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchUserMapDTO> findOne(Long id);

    /**
     * Delete the "id" branchUserMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
