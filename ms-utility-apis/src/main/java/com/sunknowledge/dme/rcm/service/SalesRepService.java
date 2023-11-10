package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesRep}.
 */
public interface SalesRepService {
    /**
     * Save a salesRep.
     *
     * @param salesRepDTO the entity to save.
     * @return the persisted entity.
     */
    SalesRepDTO save(SalesRepDTO salesRepDTO);

    /**
     * Updates a salesRep.
     *
     * @param salesRepDTO the entity to update.
     * @return the persisted entity.
     */
    SalesRepDTO update(SalesRepDTO salesRepDTO);

    /**
     * Partially updates a salesRep.
     *
     * @param salesRepDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesRepDTO> partialUpdate(SalesRepDTO salesRepDTO);

    /**
     * Get all the salesReps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesRepDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesRep.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesRepDTO> findOne(Long id);

    /**
     * Delete the "id" salesRep.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
