package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaskTypeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaskTypeMaster}.
 */
public interface TaskTypeMasterService {
    /**
     * Save a taskTypeMaster.
     *
     * @param taskTypeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    TaskTypeMasterDTO save(TaskTypeMasterDTO taskTypeMasterDTO);

    /**
     * Updates a taskTypeMaster.
     *
     * @param taskTypeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    TaskTypeMasterDTO update(TaskTypeMasterDTO taskTypeMasterDTO);

    /**
     * Partially updates a taskTypeMaster.
     *
     * @param taskTypeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskTypeMasterDTO> partialUpdate(TaskTypeMasterDTO taskTypeMasterDTO);

    /**
     * Get all the taskTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskTypeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskTypeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskTypeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" taskTypeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
