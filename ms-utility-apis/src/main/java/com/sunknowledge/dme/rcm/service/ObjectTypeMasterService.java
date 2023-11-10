package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ObjectTypeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ObjectTypeMaster}.
 */
public interface ObjectTypeMasterService {
    /**
     * Save a objectTypeMaster.
     *
     * @param objectTypeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ObjectTypeMasterDTO save(ObjectTypeMasterDTO objectTypeMasterDTO);

    /**
     * Updates a objectTypeMaster.
     *
     * @param objectTypeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ObjectTypeMasterDTO update(ObjectTypeMasterDTO objectTypeMasterDTO);

    /**
     * Partially updates a objectTypeMaster.
     *
     * @param objectTypeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ObjectTypeMasterDTO> partialUpdate(ObjectTypeMasterDTO objectTypeMasterDTO);

    /**
     * Get all the objectTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ObjectTypeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" objectTypeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjectTypeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" objectTypeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
