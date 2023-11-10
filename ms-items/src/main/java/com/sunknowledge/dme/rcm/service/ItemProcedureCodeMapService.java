package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap}.
 */
public interface ItemProcedureCodeMapService {
    /**
     * Save a itemProcedureCodeMap.
     *
     * @param itemProcedureCodeMapDTO the entity to save.
     * @return the persisted entity.
     */
    ItemProcedureCodeMapDTO save(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO);

    /**
     * Updates a itemProcedureCodeMap.
     *
     * @param itemProcedureCodeMapDTO the entity to update.
     * @return the persisted entity.
     */
    ItemProcedureCodeMapDTO update(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO);

    /**
     * Partially updates a itemProcedureCodeMap.
     *
     * @param itemProcedureCodeMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemProcedureCodeMapDTO> partialUpdate(ItemProcedureCodeMapDTO itemProcedureCodeMapDTO);

    /**
     * Get all the itemProcedureCodeMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemProcedureCodeMapDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemProcedureCodeMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemProcedureCodeMapDTO> findOne(Long id);

    /**
     * Delete the "id" itemProcedureCodeMap.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
