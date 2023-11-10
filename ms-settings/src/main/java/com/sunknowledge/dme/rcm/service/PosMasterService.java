package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PosMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PosMaster}.
 */
public interface PosMasterService {
    /**
     * Save a posMaster.
     *
     * @param posMasterDTO the entity to save.
     * @return the persisted entity.
     */
    PosMasterDTO save(PosMasterDTO posMasterDTO);

    /**
     * Updates a posMaster.
     *
     * @param posMasterDTO the entity to update.
     * @return the persisted entity.
     */
    PosMasterDTO update(PosMasterDTO posMasterDTO);

    /**
     * Partially updates a posMaster.
     *
     * @param posMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PosMasterDTO> partialUpdate(PosMasterDTO posMasterDTO);

    /**
     * Get all the posMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PosMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" posMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PosMasterDTO> findOne(Long id);

    /**
     * Delete the "id" posMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
