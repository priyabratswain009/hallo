package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemInventoryStatus}.
 */
public interface ItemInventoryStatusService {
    /**
     * Save a itemInventoryStatus.
     *
     * @param itemInventoryStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ItemInventoryStatusDTO save(ItemInventoryStatusDTO itemInventoryStatusDTO);

    /**
     * Updates a itemInventoryStatus.
     *
     * @param itemInventoryStatusDTO the entity to update.
     * @return the persisted entity.
     */
    ItemInventoryStatusDTO update(ItemInventoryStatusDTO itemInventoryStatusDTO);

    /**
     * Partially updates a itemInventoryStatus.
     *
     * @param itemInventoryStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemInventoryStatusDTO> partialUpdate(ItemInventoryStatusDTO itemInventoryStatusDTO);

    /**
     * Get all the itemInventoryStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemInventoryStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemInventoryStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemInventoryStatusDTO> findOne(Long id);

    /**
     * Delete the "id" itemInventoryStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
