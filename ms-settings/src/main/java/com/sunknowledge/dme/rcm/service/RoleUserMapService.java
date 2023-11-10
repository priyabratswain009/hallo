package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.RoleUserMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.RoleUserMap}.
 */
public interface RoleUserMapService {
    /**
     * Save a roleUserMap.
     *
     * @param roleUserMapDTO the entity to save.
     * @return the persisted entity.
     */
    RoleUserMapDTO save(RoleUserMapDTO roleUserMapDTO);

    /**
     * Updates a roleUserMap.
     *
     * @param roleUserMapDTO the entity to update.
     * @return the persisted entity.
     */
    RoleUserMapDTO update(RoleUserMapDTO roleUserMapDTO);

    /**
     * Partially updates a roleUserMap.
     *
     * @param roleUserMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoleUserMapDTO> partialUpdate(RoleUserMapDTO roleUserMapDTO);

    /**
     * Get all the roleUserMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoleUserMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" roleUserMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleUserMapDTO> findOne(Long id);

    /**
     * Delete the "id" roleUserMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
