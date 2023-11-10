package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SecondaryServiceLinesMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster}.
 */
public interface SecondaryServiceLinesMasterService {
    /**
     * Save a secondaryServiceLinesMaster.
     *
     * @param secondaryServiceLinesMasterDTO the entity to save.
     * @return the persisted entity.
     */
    SecondaryServiceLinesMasterDTO save(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO);

    /**
     * Updates a secondaryServiceLinesMaster.
     *
     * @param secondaryServiceLinesMasterDTO the entity to update.
     * @return the persisted entity.
     */
    SecondaryServiceLinesMasterDTO update(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO);

    /**
     * Partially updates a secondaryServiceLinesMaster.
     *
     * @param secondaryServiceLinesMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SecondaryServiceLinesMasterDTO> partialUpdate(SecondaryServiceLinesMasterDTO secondaryServiceLinesMasterDTO);

    /**
     * Get all the secondaryServiceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SecondaryServiceLinesMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" secondaryServiceLinesMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SecondaryServiceLinesMasterDTO> findOne(Long id);

    /**
     * Delete the "id" secondaryServiceLinesMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
