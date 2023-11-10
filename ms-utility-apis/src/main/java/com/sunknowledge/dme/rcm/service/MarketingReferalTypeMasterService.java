package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.MarketingReferalTypeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster}.
 */
public interface MarketingReferalTypeMasterService {
    /**
     * Save a marketingReferalTypeMaster.
     *
     * @param marketingReferalTypeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    MarketingReferalTypeMasterDTO save(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO);

    /**
     * Updates a marketingReferalTypeMaster.
     *
     * @param marketingReferalTypeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    MarketingReferalTypeMasterDTO update(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO);

    /**
     * Partially updates a marketingReferalTypeMaster.
     *
     * @param marketingReferalTypeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MarketingReferalTypeMasterDTO> partialUpdate(MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO);

    /**
     * Get all the marketingReferalTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MarketingReferalTypeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" marketingReferalTypeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MarketingReferalTypeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" marketingReferalTypeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
