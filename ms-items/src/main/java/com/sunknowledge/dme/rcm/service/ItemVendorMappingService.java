package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemVendorMapping}.
 */
public interface ItemVendorMappingService {
    /**
     * Save a itemVendorMapping.
     *
     * @param itemVendorMappingDTO the entity to save.
     * @return the persisted entity.
     */
    ItemVendorMappingDTO save(ItemVendorMappingDTO itemVendorMappingDTO);

    /**
     * Updates a itemVendorMapping.
     *
     * @param itemVendorMappingDTO the entity to update.
     * @return the persisted entity.
     */
    ItemVendorMappingDTO update(ItemVendorMappingDTO itemVendorMappingDTO);

    /**
     * Partially updates a itemVendorMapping.
     *
     * @param itemVendorMappingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemVendorMappingDTO> partialUpdate(ItemVendorMappingDTO itemVendorMappingDTO);

    /**
     * Get all the itemVendorMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemVendorMappingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemVendorMapping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemVendorMappingDTO> findOne(Long id);

    /**
     * Delete the "id" itemVendorMapping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
