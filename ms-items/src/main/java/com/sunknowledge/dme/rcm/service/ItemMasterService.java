package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemMaster}.
 */
public interface ItemMasterService {
    /**
     * Save a itemMaster.
     *
     * @param itemMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ItemMasterDTO save(ItemMasterDTO itemMasterDTO);

    /**
     * Updates a itemMaster.
     *
     * @param itemMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ItemMasterDTO update(ItemMasterDTO itemMasterDTO);

    /**
     * Partially updates a itemMaster.
     *
     * @param itemMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemMasterDTO> partialUpdate(ItemMasterDTO itemMasterDTO);

    /**
     * Get all the itemMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemMasterDTO> findOne(Long id);

    /**
     * Delete the "id" itemMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
