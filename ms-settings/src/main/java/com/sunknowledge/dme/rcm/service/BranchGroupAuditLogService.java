package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchGroupAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog}.
 */
public interface BranchGroupAuditLogService {
    /**
     * Save a branchGroupAuditLog.
     *
     * @param branchGroupAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    BranchGroupAuditLogDTO save(BranchGroupAuditLogDTO branchGroupAuditLogDTO);

    /**
     * Updates a branchGroupAuditLog.
     *
     * @param branchGroupAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    BranchGroupAuditLogDTO update(BranchGroupAuditLogDTO branchGroupAuditLogDTO);

    /**
     * Partially updates a branchGroupAuditLog.
     *
     * @param branchGroupAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchGroupAuditLogDTO> partialUpdate(BranchGroupAuditLogDTO branchGroupAuditLogDTO);

    /**
     * Get all the branchGroupAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchGroupAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchGroupAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchGroupAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" branchGroupAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
