package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemSerialNumber}.
 */
public interface ItemSerialNumberService {
    /**
     * Save a itemSerialNumber.
     *
     * @param itemSerialNumberDTO the entity to save.
     * @return the persisted entity.
     */
    ItemSerialNumberDTO save(ItemSerialNumberDTO itemSerialNumberDTO);

    /**
     * Updates a itemSerialNumber.
     *
     * @param itemSerialNumberDTO the entity to update.
     * @return the persisted entity.
     */
    ItemSerialNumberDTO update(ItemSerialNumberDTO itemSerialNumberDTO);

    /**
     * Partially updates a itemSerialNumber.
     *
     * @param itemSerialNumberDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemSerialNumberDTO> partialUpdate(ItemSerialNumberDTO itemSerialNumberDTO);

    /**
     * Get all the itemSerialNumbers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemSerialNumberDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemSerialNumber.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemSerialNumberDTO> findOne(Long id);

    /**
     * Delete the "id" itemSerialNumber.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
