package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemItemlocationMap}.
 */
public interface ItemItemlocationMapService {
    /**
     * Save a itemItemlocationMap.
     *
     * @param itemItemlocationMapDTO the entity to save.
     * @return the persisted entity.
     */
    ItemItemlocationMapDTO save(ItemItemlocationMapDTO itemItemlocationMapDTO);

    /**
     * Updates a itemItemlocationMap.
     *
     * @param itemItemlocationMapDTO the entity to update.
     * @return the persisted entity.
     */
    ItemItemlocationMapDTO update(ItemItemlocationMapDTO itemItemlocationMapDTO);

    /**
     * Partially updates a itemItemlocationMap.
     *
     * @param itemItemlocationMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemItemlocationMapDTO> partialUpdate(ItemItemlocationMapDTO itemItemlocationMapDTO);

    /**
     * Get all the itemItemlocationMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemItemlocationMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemItemlocationMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemItemlocationMapDTO> findOne(Long id);

    /**
     * Delete the "id" itemItemlocationMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
