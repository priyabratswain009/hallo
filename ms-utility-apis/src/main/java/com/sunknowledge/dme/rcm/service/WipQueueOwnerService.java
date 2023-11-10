package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WipQueueOwnerDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WipQueueOwner}.
 */
public interface WipQueueOwnerService {
    /**
     * Save a wipQueueOwner.
     *
     * @param wipQueueOwnerDTO the entity to save.
     * @return the persisted entity.
     */
    WipQueueOwnerDTO save(WipQueueOwnerDTO wipQueueOwnerDTO);

    /**
     * Updates a wipQueueOwner.
     *
     * @param wipQueueOwnerDTO the entity to update.
     * @return the persisted entity.
     */
    WipQueueOwnerDTO update(WipQueueOwnerDTO wipQueueOwnerDTO);

    /**
     * Partially updates a wipQueueOwner.
     *
     * @param wipQueueOwnerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WipQueueOwnerDTO> partialUpdate(WipQueueOwnerDTO wipQueueOwnerDTO);

    /**
     * Get all the wipQueueOwners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WipQueueOwnerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" wipQueueOwner.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WipQueueOwnerDTO> findOne(Long id);

    /**
     * Delete the "id" wipQueueOwner.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
