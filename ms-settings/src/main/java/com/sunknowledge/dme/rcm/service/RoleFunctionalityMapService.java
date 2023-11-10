package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.RoleFunctionalityMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap}.
 */
public interface RoleFunctionalityMapService {
    /**
     * Save a roleFunctionalityMap.
     *
     * @param roleFunctionalityMapDTO the entity to save.
     * @return the persisted entity.
     */
    RoleFunctionalityMapDTO save(RoleFunctionalityMapDTO roleFunctionalityMapDTO);

    /**
     * Updates a roleFunctionalityMap.
     *
     * @param roleFunctionalityMapDTO the entity to update.
     * @return the persisted entity.
     */
    RoleFunctionalityMapDTO update(RoleFunctionalityMapDTO roleFunctionalityMapDTO);

    /**
     * Partially updates a roleFunctionalityMap.
     *
     * @param roleFunctionalityMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoleFunctionalityMapDTO> partialUpdate(RoleFunctionalityMapDTO roleFunctionalityMapDTO);

    /**
     * Get all the roleFunctionalityMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoleFunctionalityMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" roleFunctionalityMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleFunctionalityMapDTO> findOne(Long id);

    /**
     * Delete the "id" roleFunctionalityMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
