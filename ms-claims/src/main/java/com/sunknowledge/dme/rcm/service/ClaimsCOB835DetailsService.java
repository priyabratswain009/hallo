package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835DetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsCOB835Details}.
 */
public interface ClaimsCOB835DetailsService {
    /**
     * Save a claimsCOB835Details.
     *
     * @param claimsCOB835DetailsDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsCOB835DetailsDTO save(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO);

    /**
     * Updates a claimsCOB835Details.
     *
     * @param claimsCOB835DetailsDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsCOB835DetailsDTO update(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO);

    /**
     * Partially updates a claimsCOB835Details.
     *
     * @param claimsCOB835DetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsCOB835DetailsDTO> partialUpdate(ClaimsCOB835DetailsDTO claimsCOB835DetailsDTO);

    /**
     * Get all the claimsCOB835Details.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsCOB835DetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsCOB835Details.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsCOB835DetailsDTO> findOne(Long id);

    /**
     * Delete the "id" claimsCOB835Details.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
