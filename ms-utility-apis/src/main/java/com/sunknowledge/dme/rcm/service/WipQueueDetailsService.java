package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WipQueueDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WipQueueDetails}.
 */
public interface WipQueueDetailsService {
    /**
     * Save a wipQueueDetails.
     *
     * @param wipQueueDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WipQueueDetailsDTO save(WipQueueDetailsDTO wipQueueDetailsDTO);

    /**
     * Updates a wipQueueDetails.
     *
     * @param wipQueueDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    WipQueueDetailsDTO update(WipQueueDetailsDTO wipQueueDetailsDTO);

    /**
     * Partially updates a wipQueueDetails.
     *
     * @param wipQueueDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WipQueueDetailsDTO> partialUpdate(WipQueueDetailsDTO wipQueueDetailsDTO);

    /**
     * Get all the wipQueueDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WipQueueDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" wipQueueDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WipQueueDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" wipQueueDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
