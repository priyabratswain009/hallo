package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PriceMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PriceMaster}.
 */
public interface PriceMasterService {
    /**
     * Save a priceMaster.
     *
     * @param priceMasterDTO the entity to save.
     * @return the persisted entity.
     */
    PriceMasterDTO save(PriceMasterDTO priceMasterDTO);

    /**
     * Updates a priceMaster.
     *
     * @param priceMasterDTO the entity to update.
     * @return the persisted entity.
     */
    PriceMasterDTO update(PriceMasterDTO priceMasterDTO);

    /**
     * Partially updates a priceMaster.
     *
     * @param priceMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PriceMasterDTO> partialUpdate(PriceMasterDTO priceMasterDTO);

    /**
     * Get all the priceMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PriceMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" priceMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PriceMasterDTO> findOne(Long id);

    /**
     * Delete the "id" priceMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
