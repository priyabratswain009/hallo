package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog}.
 */
public interface InsuranceGroupMasterAuditLogService {
    /**
     * Save a insuranceGroupMasterAuditLog.
     *
     * @param insuranceGroupMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceGroupMasterAuditLogDTO save(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO);

    /**
     * Updates a insuranceGroupMasterAuditLog.
     *
     * @param insuranceGroupMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceGroupMasterAuditLogDTO update(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO);

    /**
     * Partially updates a insuranceGroupMasterAuditLog.
     *
     * @param insuranceGroupMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceGroupMasterAuditLogDTO> partialUpdate(InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO);

    /**
     * Get all the insuranceGroupMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceGroupMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceGroupMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceGroupMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceGroupMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
