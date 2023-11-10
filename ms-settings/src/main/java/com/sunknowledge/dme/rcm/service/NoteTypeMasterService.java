package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.NoteTypeMaster}.
 */
public interface NoteTypeMasterService {
    /**
     * Save a noteTypeMaster.
     *
     * @param noteTypeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    NoteTypeMasterDTO save(NoteTypeMasterDTO noteTypeMasterDTO);

    /**
     * Updates a noteTypeMaster.
     *
     * @param noteTypeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    NoteTypeMasterDTO update(NoteTypeMasterDTO noteTypeMasterDTO);

    /**
     * Partially updates a noteTypeMaster.
     *
     * @param noteTypeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoteTypeMasterDTO> partialUpdate(NoteTypeMasterDTO noteTypeMasterDTO);

    /**
     * Get all the noteTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoteTypeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" noteTypeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoteTypeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" noteTypeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
