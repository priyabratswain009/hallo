package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster}.
 */
public interface InsuranceGroupMasterService {
    /**
     * Save a insuranceGroupMaster.
     *
     * @param insuranceGroupMasterDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceGroupMasterDTO save(InsuranceGroupMasterDTO insuranceGroupMasterDTO);

    /**
     * Updates a insuranceGroupMaster.
     *
     * @param insuranceGroupMasterDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceGroupMasterDTO update(InsuranceGroupMasterDTO insuranceGroupMasterDTO);

    /**
     * Partially updates a insuranceGroupMaster.
     *
     * @param insuranceGroupMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceGroupMasterDTO> partialUpdate(InsuranceGroupMasterDTO insuranceGroupMasterDTO);

    /**
     * Get all the insuranceGroupMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceGroupMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceGroupMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceGroupMasterDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceGroupMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
