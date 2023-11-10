package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchGroup}.
 */
public interface BranchGroupService {
    /**
     * Save a branchGroup.
     *
     * @param branchGroupDTO the entity to save.
     * @return the persisted entity.
     */
    BranchGroupDTO save(BranchGroupDTO branchGroupDTO);

    /**
     * Updates a branchGroup.
     *
     * @param branchGroupDTO the entity to update.
     * @return the persisted entity.
     */
    BranchGroupDTO update(BranchGroupDTO branchGroupDTO);

    /**
     * Partially updates a branchGroup.
     *
     * @param branchGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchGroupDTO> partialUpdate(BranchGroupDTO branchGroupDTO);

    /**
     * Get all the branchGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchGroupDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchGroupDTO> findOne(Long id);

    /**
     * Delete the "id" branchGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
