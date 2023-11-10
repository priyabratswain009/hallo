package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WipStatusMaster}.
 */
public interface WipStatusMasterService {
    /**
     * Save a wipStatusMaster.
     *
     * @param wipStatusMasterDTO the entity to save.
     * @return the persisted entity.
     */
    WipStatusMasterDTO save(WipStatusMasterDTO wipStatusMasterDTO);

    /**
     * Updates a wipStatusMaster.
     *
     * @param wipStatusMasterDTO the entity to update.
     * @return the persisted entity.
     */
    WipStatusMasterDTO update(WipStatusMasterDTO wipStatusMasterDTO);

    /**
     * Partially updates a wipStatusMaster.
     *
     * @param wipStatusMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WipStatusMasterDTO> partialUpdate(WipStatusMasterDTO wipStatusMasterDTO);

    /**
     * Get all the wipStatusMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WipStatusMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" wipStatusMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WipStatusMasterDTO> findOne(Long id);

    /**
     * Delete the "id" wipStatusMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
