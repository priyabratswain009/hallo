package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalityMaster}.
 */
public interface FunctionalityMasterService {
    /**
     * Save a functionalityMaster.
     *
     * @param functionalityMasterDTO the entity to save.
     * @return the persisted entity.
     */
    FunctionalityMasterDTO save(FunctionalityMasterDTO functionalityMasterDTO);

    /**
     * Updates a functionalityMaster.
     *
     * @param functionalityMasterDTO the entity to update.
     * @return the persisted entity.
     */
    FunctionalityMasterDTO update(FunctionalityMasterDTO functionalityMasterDTO);

    /**
     * Partially updates a functionalityMaster.
     *
     * @param functionalityMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FunctionalityMasterDTO> partialUpdate(FunctionalityMasterDTO functionalityMasterDTO);

    /**
     * Get all the functionalityMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FunctionalityMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" functionalityMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FunctionalityMasterDTO> findOne(Long id);

    /**
     * Delete the "id" functionalityMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
