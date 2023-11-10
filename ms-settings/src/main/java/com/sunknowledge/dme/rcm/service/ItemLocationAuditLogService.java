package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog}.
 */
public interface ItemLocationAuditLogService {
    /**
     * Save a itemLocationAuditLog.
     *
     * @param itemLocationAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemLocationAuditLogDTO save(ItemLocationAuditLogDTO itemLocationAuditLogDTO);

    /**
     * Updates a itemLocationAuditLog.
     *
     * @param itemLocationAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemLocationAuditLogDTO update(ItemLocationAuditLogDTO itemLocationAuditLogDTO);

    /**
     * Partially updates a itemLocationAuditLog.
     *
     * @param itemLocationAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemLocationAuditLogDTO> partialUpdate(ItemLocationAuditLogDTO itemLocationAuditLogDTO);

    /**
     * Get all the itemLocationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemLocationAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemLocationAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemLocationAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemLocationAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
