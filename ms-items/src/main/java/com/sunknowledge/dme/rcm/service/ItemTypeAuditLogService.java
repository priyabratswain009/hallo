package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog}.
 */
public interface ItemTypeAuditLogService {
    /**
     * Save a itemTypeAuditLog.
     *
     * @param itemTypeAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemTypeAuditLogDTO save(ItemTypeAuditLogDTO itemTypeAuditLogDTO);

    /**
     * Updates a itemTypeAuditLog.
     *
     * @param itemTypeAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemTypeAuditLogDTO update(ItemTypeAuditLogDTO itemTypeAuditLogDTO);

    /**
     * Partially updates a itemTypeAuditLog.
     *
     * @param itemTypeAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemTypeAuditLogDTO> partialUpdate(ItemTypeAuditLogDTO itemTypeAuditLogDTO);

    /**
     * Get all the itemTypeAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemTypeAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemTypeAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemTypeAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemTypeAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
