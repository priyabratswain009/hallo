package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog}.
 */
public interface ItemSerialNumberAuditLogService {
    /**
     * Save a itemSerialNumberAuditLog.
     *
     * @param itemSerialNumberAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemSerialNumberAuditLogDTO save(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO);

    /**
     * Updates a itemSerialNumberAuditLog.
     *
     * @param itemSerialNumberAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemSerialNumberAuditLogDTO update(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO);

    /**
     * Partially updates a itemSerialNumberAuditLog.
     *
     * @param itemSerialNumberAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemSerialNumberAuditLogDTO> partialUpdate(ItemSerialNumberAuditLogDTO itemSerialNumberAuditLogDTO);

    /**
     * Get all the itemSerialNumberAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemSerialNumberAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemSerialNumberAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemSerialNumberAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemSerialNumberAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
