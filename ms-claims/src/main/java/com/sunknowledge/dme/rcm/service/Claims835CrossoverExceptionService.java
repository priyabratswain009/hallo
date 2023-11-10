package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverExceptionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.Claims835CrossoverException}.
 */
public interface Claims835CrossoverExceptionService {
    /**
     * Save a claims835CrossoverException.
     *
     * @param claims835CrossoverExceptionDTO the entity to save.
     * @return the persisted entity.
     */
    Claims835CrossoverExceptionDTO save(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO);

    /**
     * Updates a claims835CrossoverException.
     *
     * @param claims835CrossoverExceptionDTO the entity to update.
     * @return the persisted entity.
     */
    Claims835CrossoverExceptionDTO update(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO);

    /**
     * Partially updates a claims835CrossoverException.
     *
     * @param claims835CrossoverExceptionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Claims835CrossoverExceptionDTO> partialUpdate(Claims835CrossoverExceptionDTO claims835CrossoverExceptionDTO);

    /**
     * Get all the claims835CrossoverExceptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Claims835CrossoverExceptionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claims835CrossoverException.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Claims835CrossoverExceptionDTO> findOne(Long id);

    /**
     * Delete the "id" claims835CrossoverException.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
