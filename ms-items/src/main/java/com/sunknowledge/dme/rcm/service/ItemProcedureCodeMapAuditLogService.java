package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog}.
 */
public interface ItemProcedureCodeMapAuditLogService {
    /**
     * Save a itemProcedureCodeMapAuditLog.
     *
     * @param itemProcedureCodeMapAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ItemProcedureCodeMapAuditLogDTO save(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO);

    /**
     * Updates a itemProcedureCodeMapAuditLog.
     *
     * @param itemProcedureCodeMapAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ItemProcedureCodeMapAuditLogDTO update(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO);

    /**
     * Partially updates a itemProcedureCodeMapAuditLog.
     *
     * @param itemProcedureCodeMapAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ItemProcedureCodeMapAuditLogDTO> partialUpdate(ItemProcedureCodeMapAuditLogDTO itemProcedureCodeMapAuditLogDTO);

    /**
     * Get all the itemProcedureCodeMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ItemProcedureCodeMapAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" itemProcedureCodeMapAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemProcedureCodeMapAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" itemProcedureCodeMapAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
