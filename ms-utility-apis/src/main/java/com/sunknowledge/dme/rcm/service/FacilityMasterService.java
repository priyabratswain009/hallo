package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.FacilityMaster}.
 */
public interface FacilityMasterService {
    /**
     * Save a facilityMaster.
     *
     * @param facilityMasterDTO the entity to save.
     * @return the persisted entity.
     */
    FacilityMasterDTO save(FacilityMasterDTO facilityMasterDTO);

    /**
     * Updates a facilityMaster.
     *
     * @param facilityMasterDTO the entity to update.
     * @return the persisted entity.
     */
    FacilityMasterDTO update(FacilityMasterDTO facilityMasterDTO);

    /**
     * Partially updates a facilityMaster.
     *
     * @param facilityMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FacilityMasterDTO> partialUpdate(FacilityMasterDTO facilityMasterDTO);

    /**
     * Get all the facilityMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FacilityMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" facilityMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FacilityMasterDTO> findOne(Long id);

    /**
     * Delete the "id" facilityMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
