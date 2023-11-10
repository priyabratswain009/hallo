package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog}.
 */
public interface WipStatusMasterAuditLogService {
    /**
     * Save a wipStatusMasterAuditLog.
     *
     * @param wipStatusMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    WipStatusMasterAuditLogDTO save(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO);

    /**
     * Updates a wipStatusMasterAuditLog.
     *
     * @param wipStatusMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    WipStatusMasterAuditLogDTO update(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO);

    /**
     * Partially updates a wipStatusMasterAuditLog.
     *
     * @param wipStatusMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WipStatusMasterAuditLogDTO> partialUpdate(WipStatusMasterAuditLogDTO wipStatusMasterAuditLogDTO);

    /**
     * Get all the wipStatusMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WipStatusMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" wipStatusMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WipStatusMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" wipStatusMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
