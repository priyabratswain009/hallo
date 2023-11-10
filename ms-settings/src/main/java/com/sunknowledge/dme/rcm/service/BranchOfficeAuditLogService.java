package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchOfficeAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog}.
 */
public interface BranchOfficeAuditLogService {
    /**
     * Save a branchOfficeAuditLog.
     *
     * @param branchOfficeAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    BranchOfficeAuditLogDTO save(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO);

    /**
     * Updates a branchOfficeAuditLog.
     *
     * @param branchOfficeAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    BranchOfficeAuditLogDTO update(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO);

    /**
     * Partially updates a branchOfficeAuditLog.
     *
     * @param branchOfficeAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchOfficeAuditLogDTO> partialUpdate(BranchOfficeAuditLogDTO branchOfficeAuditLogDTO);

    /**
     * Get all the branchOfficeAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchOfficeAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchOfficeAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchOfficeAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" branchOfficeAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
