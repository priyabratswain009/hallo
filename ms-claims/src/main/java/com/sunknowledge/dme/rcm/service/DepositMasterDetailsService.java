package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DepositMasterDetails}.
 */
public interface DepositMasterDetailsService {
    /**
     * Save a depositMasterDetails.
     *
     * @param depositMasterDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    DepositMasterDetailsDTO save(DepositMasterDetailsDTO depositMasterDetailsDTO);

    /**
     * Updates a depositMasterDetails.
     *
     * @param depositMasterDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    DepositMasterDetailsDTO update(DepositMasterDetailsDTO depositMasterDetailsDTO);

    /**
     * Partially updates a depositMasterDetails.
     *
     * @param depositMasterDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DepositMasterDetailsDTO> partialUpdate(DepositMasterDetailsDTO depositMasterDetailsDTO);

    /**
     * Get all the depositMasterDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DepositMasterDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" depositMasterDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepositMasterDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" depositMasterDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
