package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.HoldReasonMaster}.
 */
public interface HoldReasonMasterService {
    /**
     * Save a holdReasonMaster.
     *
     * @param holdReasonMasterDTO the entity to save.
     * @return the persisted entity.
     */
    HoldReasonMasterDTO save(HoldReasonMasterDTO holdReasonMasterDTO);

    /**
     * Updates a holdReasonMaster.
     *
     * @param holdReasonMasterDTO the entity to update.
     * @return the persisted entity.
     */
    HoldReasonMasterDTO update(HoldReasonMasterDTO holdReasonMasterDTO);

    /**
     * Partially updates a holdReasonMaster.
     *
     * @param holdReasonMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HoldReasonMasterDTO> partialUpdate(HoldReasonMasterDTO holdReasonMasterDTO);

    /**
     * Get all the holdReasonMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HoldReasonMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" holdReasonMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HoldReasonMasterDTO> findOne(Long id);

    /**
     * Delete the "id" holdReasonMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
