package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PayerMaster}.
 */
public interface PayerMasterService {
    /**
     * Save a payerMaster.
     *
     * @param payerMasterDTO the entity to save.
     * @return the persisted entity.
     */
    PayerMasterDTO save(PayerMasterDTO payerMasterDTO);

    /**
     * Updates a payerMaster.
     *
     * @param payerMasterDTO the entity to update.
     * @return the persisted entity.
     */
    PayerMasterDTO update(PayerMasterDTO payerMasterDTO);

    /**
     * Partially updates a payerMaster.
     *
     * @param payerMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PayerMasterDTO> partialUpdate(PayerMasterDTO payerMasterDTO);

    /**
     * Get all the payerMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayerMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" payerMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayerMasterDTO> findOne(Long id);

    /**
     * Delete the "id" payerMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
