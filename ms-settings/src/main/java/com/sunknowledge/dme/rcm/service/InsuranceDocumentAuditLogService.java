package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog}.
 */
public interface InsuranceDocumentAuditLogService {
    /**
     * Save a insuranceDocumentAuditLog.
     *
     * @param insuranceDocumentAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceDocumentAuditLogDTO save(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO);

    /**
     * Updates a insuranceDocumentAuditLog.
     *
     * @param insuranceDocumentAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceDocumentAuditLogDTO update(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO);

    /**
     * Partially updates a insuranceDocumentAuditLog.
     *
     * @param insuranceDocumentAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceDocumentAuditLogDTO> partialUpdate(InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO);

    /**
     * Get all the insuranceDocumentAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceDocumentAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceDocumentAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceDocumentAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceDocumentAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
