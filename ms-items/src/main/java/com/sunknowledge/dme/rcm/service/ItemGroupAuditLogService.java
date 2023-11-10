package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog}.
 */
public interface ItemGroupAuditLogService {
    /**
     * Save a itemGroupAuditLog.
     *
     * @param itemGroupAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemGroupAuditLogDTO save(ItemGroupAuditLogDTO itemGroupAuditLogDTO);

    /**
     * Updates a itemGroupAuditLog.
     *
     * @param itemGroupAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemGroupAuditLogDTO update(ItemGroupAuditLogDTO itemGroupAuditLogDTO);

    /**
     * Partially updates a itemGroupAuditLog.
     *
     * @param itemGroupAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemGroupAuditLogDTO> partialUpdate(ItemGroupAuditLogDTO itemGroupAuditLogDTO);

    /**
     * Get all the itemGroupAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemGroupAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemGroupAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemGroupAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemGroupAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
