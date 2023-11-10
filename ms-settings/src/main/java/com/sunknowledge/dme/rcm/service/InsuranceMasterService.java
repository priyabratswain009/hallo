package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceMaster}.
 */
public interface InsuranceMasterService {
    /**
     * Save a insuranceMaster.
     *
     * @param insuranceMasterDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceMasterDTO save(InsuranceMasterDTO insuranceMasterDTO);

    /**
     * Updates a insuranceMaster.
     *
     * @param insuranceMasterDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceMasterDTO update(InsuranceMasterDTO insuranceMasterDTO);

    /**
     * Partially updates a insuranceMaster.
     *
     * @param insuranceMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceMasterDTO> partialUpdate(InsuranceMasterDTO insuranceMasterDTO);

    /**
     * Get all the insuranceMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceMasterDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
