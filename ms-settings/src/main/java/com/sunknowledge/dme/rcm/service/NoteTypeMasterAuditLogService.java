package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.NoteTypeMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog}.
 */
public interface NoteTypeMasterAuditLogService {
    /**
     * Save a noteTypeMasterAuditLog.
     *
     * @param noteTypeMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    NoteTypeMasterAuditLogDTO save(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO);

    /**
     * Updates a noteTypeMasterAuditLog.
     *
     * @param noteTypeMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    NoteTypeMasterAuditLogDTO update(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO);

    /**
     * Partially updates a noteTypeMasterAuditLog.
     *
     * @param noteTypeMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NoteTypeMasterAuditLogDTO> partialUpdate(NoteTypeMasterAuditLogDTO noteTypeMasterAuditLogDTO);

    /**
     * Get all the noteTypeMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NoteTypeMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" noteTypeMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NoteTypeMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" noteTypeMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
