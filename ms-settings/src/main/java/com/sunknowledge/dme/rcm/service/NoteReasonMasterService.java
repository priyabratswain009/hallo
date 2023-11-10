package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.NoteReasonMaster}.
 */
public interface NoteReasonMasterService {
    /**
     * Save a noteReasonMaster.
     *
     * @param noteReasonMasterDTO the entity to save.
     * @return the persisted entity.
     */
    NoteReasonMasterDTO save(NoteReasonMasterDTO noteReasonMasterDTO);

    /**
     * Updates a noteReasonMaster.
     *
     * @param noteReasonMasterDTO the entity to update.
     * @return the persisted entity.
     */
    NoteReasonMasterDTO update(NoteReasonMasterDTO noteReasonMasterDTO);

    /**
     * Partially updates a noteReasonMaster.
     *
     * @param noteReasonMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoteReasonMasterDTO> partialUpdate(NoteReasonMasterDTO noteReasonMasterDTO);

    /**
     * Get all the noteReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoteReasonMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" noteReasonMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoteReasonMasterDTO> findOne(Long id);

    /**
     * Delete the "id" noteReasonMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
