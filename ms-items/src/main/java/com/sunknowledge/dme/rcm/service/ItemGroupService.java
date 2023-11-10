package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemGroup}.
 */
public interface ItemGroupService {
    /**
     * Save a itemGroup.
     *
     * @param itemGroupDTO the entity to save.
     * @return the persisted entity.
     */
    ItemGroupDTO save(ItemGroupDTO itemGroupDTO);

    /**
     * Updates a itemGroup.
     *
     * @param itemGroupDTO the entity to update.
     * @return the persisted entity.
     */
    ItemGroupDTO update(ItemGroupDTO itemGroupDTO);

    /**
     * Partially updates a itemGroup.
     *
     * @param itemGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemGroupDTO> partialUpdate(ItemGroupDTO itemGroupDTO);

    /**
     * Get all the itemGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemGroupDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemGroupDTO> findOne(Long id);

    /**
     * Delete the "id" itemGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
