package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PriceDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetails}.
 */
public interface PriceDetailsService {
    /**
     * Save a priceDetails.
     *
     * @param priceDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    PriceDetailsDTO save(PriceDetailsDTO priceDetailsDTO);

    /**
     * Updates a priceDetails.
     *
     * @param priceDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    PriceDetailsDTO update(PriceDetailsDTO priceDetailsDTO);

    /**
     * Partially updates a priceDetails.
     *
     * @param priceDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PriceDetailsDTO> partialUpdate(PriceDetailsDTO priceDetailsDTO);

    /**
     * Get all the priceDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PriceDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" priceDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PriceDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" priceDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
