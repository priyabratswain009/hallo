package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog}.
 */
public interface ItemMasterAuditLogService {
    /**
     * Save a itemMasterAuditLog.
     *
     * @param itemMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemMasterAuditLogDTO save(ItemMasterAuditLogDTO itemMasterAuditLogDTO);

    /**
     * Updates a itemMasterAuditLog.
     *
     * @param itemMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemMasterAuditLogDTO update(ItemMasterAuditLogDTO itemMasterAuditLogDTO);

    /**
     * Partially updates a itemMasterAuditLog.
     *
     * @param itemMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemMasterAuditLogDTO> partialUpdate(ItemMasterAuditLogDTO itemMasterAuditLogDTO);

    /**
     * Get all the itemMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
