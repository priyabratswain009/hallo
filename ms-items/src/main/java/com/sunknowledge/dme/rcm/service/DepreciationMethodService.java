package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DepreciationMethodDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DepreciationMethod}.
 */
public interface DepreciationMethodService {
    /**
     * Save a depreciationMethod.
     *
     * @param depreciationMethodDTO the entity to save.
     * @return the persisted entity.
     */
    DepreciationMethodDTO save(DepreciationMethodDTO depreciationMethodDTO);

    /**
     * Updates a depreciationMethod.
     *
     * @param depreciationMethodDTO the entity to update.
     * @return the persisted entity.
     */
    DepreciationMethodDTO update(DepreciationMethodDTO depreciationMethodDTO);

    /**
     * Partially updates a depreciationMethod.
     *
     * @param depreciationMethodDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DepreciationMethodDTO> partialUpdate(DepreciationMethodDTO depreciationMethodDTO);

    /**
     * Get all the depreciationMethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DepreciationMethodDTO> findAll(Pageable pageable);

    /**
     * Get the "id" depreciationMethod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepreciationMethodDTO> findOne(Long id);

    /**
     * Delete the "id" depreciationMethod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
