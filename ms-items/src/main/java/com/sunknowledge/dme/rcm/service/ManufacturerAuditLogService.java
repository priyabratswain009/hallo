package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog}.
 */
public interface ManufacturerAuditLogService {
    /**
     * Save a manufacturerAuditLog.
     *
     * @param manufacturerAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    ManufacturerAuditLogDTO save(ManufacturerAuditLogDTO manufacturerAuditLogDTO);

    /**
     * Updates a manufacturerAuditLog.
     *
     * @param manufacturerAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    ManufacturerAuditLogDTO update(ManufacturerAuditLogDTO manufacturerAuditLogDTO);

    /**
     * Partially updates a manufacturerAuditLog.
     *
     * @param manufacturerAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ManufacturerAuditLogDTO> partialUpdate(ManufacturerAuditLogDTO manufacturerAuditLogDTO);

    /**
     * Get all the manufacturerAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ManufacturerAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" manufacturerAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ManufacturerAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" manufacturerAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
