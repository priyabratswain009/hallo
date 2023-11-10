package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaxonomyDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaxonomyDetails}.
 */
public interface TaxonomyDetailsService {
    /**
     * Save a taxonomyDetails.
     *
     * @param taxonomyDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    TaxonomyDetailsDTO save(TaxonomyDetailsDTO taxonomyDetailsDTO);

    /**
     * Updates a taxonomyDetails.
     *
     * @param taxonomyDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    TaxonomyDetailsDTO update(TaxonomyDetailsDTO taxonomyDetailsDTO);

    /**
     * Partially updates a taxonomyDetails.
     *
     * @param taxonomyDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaxonomyDetailsDTO> partialUpdate(TaxonomyDetailsDTO taxonomyDetailsDTO);

    /**
     * Get all the taxonomyDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaxonomyDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taxonomyDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxonomyDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" taxonomyDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
