package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaskReasonMaster}.
 */
public interface TaskReasonMasterService {
    /**
     * Save a taskReasonMaster.
     *
     * @param taskReasonMasterDTO the entity to save.
     * @return the persisted entity.
     */
    TaskReasonMasterDTO save(TaskReasonMasterDTO taskReasonMasterDTO);

    /**
     * Updates a taskReasonMaster.
     *
     * @param taskReasonMasterDTO the entity to update.
     * @return the persisted entity.
     */
    TaskReasonMasterDTO update(TaskReasonMasterDTO taskReasonMasterDTO);

    /**
     * Partially updates a taskReasonMaster.
     *
     * @param taskReasonMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskReasonMasterDTO> partialUpdate(TaskReasonMasterDTO taskReasonMasterDTO);

    /**
     * Get all the taskReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskReasonMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskReasonMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskReasonMasterDTO> findOne(Long id);

    /**
     * Delete the "id" taskReasonMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
