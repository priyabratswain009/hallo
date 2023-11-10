package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.Transaction835MasterDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.Transaction835MasterDetails}.
 */
public interface Transaction835MasterDetailsService {
    /**
     * Save a transaction835MasterDetails.
     *
     * @param transaction835MasterDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    Transaction835MasterDetailsDTO save(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO);

    /**
     * Updates a transaction835MasterDetails.
     *
     * @param transaction835MasterDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    Transaction835MasterDetailsDTO update(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO);

    /**
     * Partially updates a transaction835MasterDetails.
     *
     * @param transaction835MasterDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Transaction835MasterDetailsDTO> partialUpdate(Transaction835MasterDetailsDTO transaction835MasterDetailsDTO);

    /**
     * Get all the transaction835MasterDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Transaction835MasterDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" transaction835MasterDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Transaction835MasterDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" transaction835MasterDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
