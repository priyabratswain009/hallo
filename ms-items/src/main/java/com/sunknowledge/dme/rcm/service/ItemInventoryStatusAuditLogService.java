package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog}.
 */
public interface ItemInventoryStatusAuditLogService {
    /**
     * Save a itemInventoryStatusAuditLog.
     *
     * @param itemInventoryStatusAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemInventoryStatusAuditLogDTO save(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO);

    /**
     * Updates a itemInventoryStatusAuditLog.
     *
     * @param itemInventoryStatusAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemInventoryStatusAuditLogDTO update(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO);

    /**
     * Partially updates a itemInventoryStatusAuditLog.
     *
     * @param itemInventoryStatusAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemInventoryStatusAuditLogDTO> partialUpdate(ItemInventoryStatusAuditLogDTO itemInventoryStatusAuditLogDTO);

    /**
     * Get all the itemInventoryStatusAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemInventoryStatusAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemInventoryStatusAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemInventoryStatusAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemInventoryStatusAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
