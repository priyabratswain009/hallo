package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap}.
 */
public interface ItemAssetNumberMapService {
    /**
     * Save a itemAssetNumberMap.
     *
     * @param itemAssetNumberMapDTO the entity to save.
     * @return the persisted entity.
     */
    ItemAssetNumberMapDTO save(ItemAssetNumberMapDTO itemAssetNumberMapDTO);

    /**
     * Updates a itemAssetNumberMap.
     *
     * @param itemAssetNumberMapDTO the entity to update.
     * @return the persisted entity.
     */
    ItemAssetNumberMapDTO update(ItemAssetNumberMapDTO itemAssetNumberMapDTO);

    /**
     * Partially updates a itemAssetNumberMap.
     *
     * @param itemAssetNumberMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemAssetNumberMapDTO> partialUpdate(ItemAssetNumberMapDTO itemAssetNumberMapDTO);

    /**
     * Get all the itemAssetNumberMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemAssetNumberMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemAssetNumberMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemAssetNumberMapDTO> findOne(Long id);

    /**
     * Delete the "id" itemAssetNumberMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
