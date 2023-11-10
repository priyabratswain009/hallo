package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog}.
 */
public interface BranchInsuranceMapAuditLogService {
    /**
     * Save a branchInsuranceMapAuditLog.
     *
     * @param branchInsuranceMapAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    BranchInsuranceMapAuditLogDTO save(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO);

    /**
     * Updates a branchInsuranceMapAuditLog.
     *
     * @param branchInsuranceMapAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    BranchInsuranceMapAuditLogDTO update(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO);

    /**
     * Partially updates a branchInsuranceMapAuditLog.
     *
     * @param branchInsuranceMapAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchInsuranceMapAuditLogDTO> partialUpdate(BranchInsuranceMapAuditLogDTO branchInsuranceMapAuditLogDTO);

    /**
     * Get all the branchInsuranceMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchInsuranceMapAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchInsuranceMapAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchInsuranceMapAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" branchInsuranceMapAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
