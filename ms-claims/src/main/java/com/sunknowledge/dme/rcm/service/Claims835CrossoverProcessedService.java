package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverProcessedDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed}.
 */
public interface Claims835CrossoverProcessedService {
    /**
     * Save a claims835CrossoverProcessed.
     *
     * @param claims835CrossoverProcessedDTO the entity to save.
     * @return the persisted entity.
     */
    Claims835CrossoverProcessedDTO save(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO);

    /**
     * Updates a claims835CrossoverProcessed.
     *
     * @param claims835CrossoverProcessedDTO the entity to update.
     * @return the persisted entity.
     */
    Claims835CrossoverProcessedDTO update(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO);

    /**
     * Partially updates a claims835CrossoverProcessed.
     *
     * @param claims835CrossoverProcessedDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Claims835CrossoverProcessedDTO> partialUpdate(Claims835CrossoverProcessedDTO claims835CrossoverProcessedDTO);

    /**
     * Get all the claims835CrossoverProcesseds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Claims835CrossoverProcessedDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claims835CrossoverProcessed.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Claims835CrossoverProcessedDTO> findOne(Long id);

    /**
     * Delete the "id" claims835CrossoverProcessed.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
