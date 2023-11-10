package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog}.
 */
public interface ClaimProgramMasterAuditLogService {
    /**
     * Save a claimProgramMasterAuditLog.
     *
     * @param claimProgramMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimProgramMasterAuditLogDTO save(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO);

    /**
     * Updates a claimProgramMasterAuditLog.
     *
     * @param claimProgramMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimProgramMasterAuditLogDTO update(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO);

    /**
     * Partially updates a claimProgramMasterAuditLog.
     *
     * @param claimProgramMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimProgramMasterAuditLogDTO> partialUpdate(ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO);

    /**
     * Get all the claimProgramMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimProgramMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimProgramMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimProgramMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" claimProgramMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
