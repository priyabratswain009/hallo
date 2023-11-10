package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.NoteReasonMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog}.
 */
public interface NoteReasonMasterAuditLogService {
    /**
     * Save a noteReasonMasterAuditLog.
     *
     * @param noteReasonMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    NoteReasonMasterAuditLogDTO save(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO);

    /**
     * Updates a noteReasonMasterAuditLog.
     *
     * @param noteReasonMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    NoteReasonMasterAuditLogDTO update(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO);

    /**
     * Partially updates a noteReasonMasterAuditLog.
     *
     * @param noteReasonMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoteReasonMasterAuditLogDTO> partialUpdate(NoteReasonMasterAuditLogDTO noteReasonMasterAuditLogDTO);

    /**
     * Get all the noteReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoteReasonMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" noteReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoteReasonMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" noteReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
