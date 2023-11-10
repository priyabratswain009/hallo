package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaxZone}.
 */
public interface TaxZoneService {
    /**
     * Save a taxZone.
     *
     * @param taxZoneDTO the entity to save.
     * @return the persisted entity.
     */
    TaxZoneDTO save(TaxZoneDTO taxZoneDTO);

    /**
     * Updates a taxZone.
     *
     * @param taxZoneDTO the entity to update.
     * @return the persisted entity.
     */
    TaxZoneDTO update(TaxZoneDTO taxZoneDTO);

    /**
     * Partially updates a taxZone.
     *
     * @param taxZoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaxZoneDTO> partialUpdate(TaxZoneDTO taxZoneDTO);

    /**
     * Get all the taxZones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaxZoneDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taxZone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxZoneDTO> findOne(Long id);

    /**
     * Delete the "id" taxZone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
