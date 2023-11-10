package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog}.
 */
public interface DocumentTypeMasterAuditLogService {
    /**
     * Save a documentTypeMasterAuditLog.
     *
     * @param documentTypeMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentTypeMasterAuditLogDTO save(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO);

    /**
     * Updates a documentTypeMasterAuditLog.
     *
     * @param documentTypeMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    DocumentTypeMasterAuditLogDTO update(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO);

    /**
     * Partially updates a documentTypeMasterAuditLog.
     *
     * @param documentTypeMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DocumentTypeMasterAuditLogDTO> partialUpdate(DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO);

    /**
     * Get all the documentTypeMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DocumentTypeMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" documentTypeMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentTypeMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" documentTypeMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
