package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaskCommentsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaskComments}.
 */
public interface TaskCommentsService {
    /**
     * Save a taskComments.
     *
     * @param taskCommentsDTO the entity to save.
     * @return the persisted entity.
     */
    TaskCommentsDTO save(TaskCommentsDTO taskCommentsDTO);

    /**
     * Updates a taskComments.
     *
     * @param taskCommentsDTO the entity to update.
     * @return the persisted entity.
     */
    TaskCommentsDTO update(TaskCommentsDTO taskCommentsDTO);

    /**
     * Partially updates a taskComments.
     *
     * @param taskCommentsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskCommentsDTO> partialUpdate(TaskCommentsDTO taskCommentsDTO);

    /**
     * Get all the taskComments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskCommentsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskComments.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskCommentsDTO> findOne(Long id);

    /**
     * Delete the "id" taskComments.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
