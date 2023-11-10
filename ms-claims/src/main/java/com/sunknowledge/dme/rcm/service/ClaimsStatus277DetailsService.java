package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsStatus277DetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsStatus277Details}.
 */
public interface ClaimsStatus277DetailsService {
    /**
     * Save a claimsStatus277Details.
     *
     * @param claimsStatus277DetailsDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsStatus277DetailsDTO save(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO);

    /**
     * Updates a claimsStatus277Details.
     *
     * @param claimsStatus277DetailsDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsStatus277DetailsDTO update(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO);

    /**
     * Partially updates a claimsStatus277Details.
     *
     * @param claimsStatus277DetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsStatus277DetailsDTO> partialUpdate(ClaimsStatus277DetailsDTO claimsStatus277DetailsDTO);

    /**
     * Get all the claimsStatus277Details.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsStatus277DetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsStatus277Details.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsStatus277DetailsDTO> findOne(Long id);

    /**
     * Delete the "id" claimsStatus277Details.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
