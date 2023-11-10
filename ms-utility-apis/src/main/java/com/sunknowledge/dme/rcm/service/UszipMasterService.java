package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.UszipMaster}.
 */
public interface UszipMasterService {
    /**
     * Save a uszipMaster.
     *
     * @param uszipMasterDTO the entity to save.
     * @return the persisted entity.
     */
    UszipMasterDTO save(UszipMasterDTO uszipMasterDTO);

    /**
     * Updates a uszipMaster.
     *
     * @param uszipMasterDTO the entity to update.
     * @return the persisted entity.
     */
    UszipMasterDTO update(UszipMasterDTO uszipMasterDTO);

    /**
     * Partially updates a uszipMaster.
     *
     * @param uszipMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UszipMasterDTO> partialUpdate(UszipMasterDTO uszipMasterDTO);

    /**
     * Get all the uszipMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UszipMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" uszipMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UszipMasterDTO> findOne(Long id);

    /**
     * Delete the "id" uszipMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
