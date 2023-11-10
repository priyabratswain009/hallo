package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog}.
 */
public interface BranchItemLocationMapAuditLogService {
    /**
     * Save a branchItemLocationMapAuditLog.
     *
     * @param branchItemLocationMapAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    BranchItemLocationMapAuditLogDTO save(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO);

    /**
     * Updates a branchItemLocationMapAuditLog.
     *
     * @param branchItemLocationMapAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    BranchItemLocationMapAuditLogDTO update(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO);

    /**
     * Partially updates a branchItemLocationMapAuditLog.
     *
     * @param branchItemLocationMapAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchItemLocationMapAuditLogDTO> partialUpdate(BranchItemLocationMapAuditLogDTO branchItemLocationMapAuditLogDTO);

    /**
     * Get all the branchItemLocationMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchItemLocationMapAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchItemLocationMapAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchItemLocationMapAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" branchItemLocationMapAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
