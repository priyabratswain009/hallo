package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog}.
 */
public interface ClaimFormMasterAuditLogService {
    /**
     * Save a claimFormMasterAuditLog.
     *
     * @param claimFormMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimFormMasterAuditLogDTO save(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO);

    /**
     * Updates a claimFormMasterAuditLog.
     *
     * @param claimFormMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimFormMasterAuditLogDTO update(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO);

    /**
     * Partially updates a claimFormMasterAuditLog.
     *
     * @param claimFormMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimFormMasterAuditLogDTO> partialUpdate(ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO);

    /**
     * Get all the claimFormMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimFormMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimFormMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimFormMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" claimFormMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
