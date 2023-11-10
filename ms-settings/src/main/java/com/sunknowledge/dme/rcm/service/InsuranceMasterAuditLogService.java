package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog}.
 */
public interface InsuranceMasterAuditLogService {
    /**
     * Save a insuranceMasterAuditLog.
     *
     * @param insuranceMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceMasterAuditLogDTO save(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO);

    /**
     * Updates a insuranceMasterAuditLog.
     *
     * @param insuranceMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceMasterAuditLogDTO update(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO);

    /**
     * Partially updates a insuranceMasterAuditLog.
     *
     * @param insuranceMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceMasterAuditLogDTO> partialUpdate(InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO);

    /**
     * Get all the insuranceMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
