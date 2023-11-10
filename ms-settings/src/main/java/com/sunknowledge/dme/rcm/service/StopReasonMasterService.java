package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StopReasonMaster}.
 */
public interface StopReasonMasterService {
    /**
     * Save a stopReasonMaster.
     *
     * @param stopReasonMasterDTO the entity to save.
     * @return the persisted entity.
     */
    StopReasonMasterDTO save(StopReasonMasterDTO stopReasonMasterDTO);

    /**
     * Updates a stopReasonMaster.
     *
     * @param stopReasonMasterDTO the entity to update.
     * @return the persisted entity.
     */
    StopReasonMasterDTO update(StopReasonMasterDTO stopReasonMasterDTO);

    /**
     * Partially updates a stopReasonMaster.
     *
     * @param stopReasonMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StopReasonMasterDTO> partialUpdate(StopReasonMasterDTO stopReasonMasterDTO);

    /**
     * Get all the stopReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StopReasonMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stopReasonMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StopReasonMasterDTO> findOne(Long id);

    /**
     * Delete the "id" stopReasonMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
