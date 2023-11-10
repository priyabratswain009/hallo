package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog}.
 */
public interface SalesOrderClassificationAuditLogService {
    /**
     * Save a salesOrderClassificationAuditLog.
     *
     * @param salesOrderClassificationAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    SalesOrderClassificationAuditLogDTO save(SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO);

    /**
     * Updates a salesOrderClassificationAuditLog.
     *
     * @param salesOrderClassificationAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    SalesOrderClassificationAuditLogDTO update(SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO);

    /**
     * Partially updates a salesOrderClassificationAuditLog.
     *
     * @param salesOrderClassificationAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesOrderClassificationAuditLogDTO> partialUpdate(SalesOrderClassificationAuditLogDTO salesOrderClassificationAuditLogDTO);

    /**
     * Get all the salesOrderClassificationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesOrderClassificationAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesOrderClassificationAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesOrderClassificationAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderClassificationAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
