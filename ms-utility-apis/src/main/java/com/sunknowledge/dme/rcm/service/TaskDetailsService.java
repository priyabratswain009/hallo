package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaskDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaskDetails}.
 */
public interface TaskDetailsService {
    /**
     * Save a taskDetails.
     *
     * @param taskDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    TaskDetailsDTO save(TaskDetailsDTO taskDetailsDTO);

    /**
     * Updates a taskDetails.
     *
     * @param taskDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    TaskDetailsDTO update(TaskDetailsDTO taskDetailsDTO);

    /**
     * Partially updates a taskDetails.
     *
     * @param taskDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskDetailsDTO> partialUpdate(TaskDetailsDTO taskDetailsDTO);

    /**
     * Get all the taskDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" taskDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
