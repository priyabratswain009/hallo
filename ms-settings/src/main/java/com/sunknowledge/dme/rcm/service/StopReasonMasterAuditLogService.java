package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog}.
 */
public interface StopReasonMasterAuditLogService {
    /**
     * Save a stopReasonMasterAuditLog.
     *
     * @param stopReasonMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    StopReasonMasterAuditLogDTO save(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO);

    /**
     * Updates a stopReasonMasterAuditLog.
     *
     * @param stopReasonMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    StopReasonMasterAuditLogDTO update(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO);

    /**
     * Partially updates a stopReasonMasterAuditLog.
     *
     * @param stopReasonMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StopReasonMasterAuditLogDTO> partialUpdate(StopReasonMasterAuditLogDTO stopReasonMasterAuditLogDTO);

    /**
     * Get all the stopReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StopReasonMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stopReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StopReasonMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" stopReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
