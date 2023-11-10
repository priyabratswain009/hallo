package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaxZoneAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog}.
 */
public interface TaxZoneAuditLogService {
    /**
     * Save a taxZoneAuditLog.
     *
     * @param taxZoneAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    TaxZoneAuditLogDTO save(TaxZoneAuditLogDTO taxZoneAuditLogDTO);

    /**
     * Updates a taxZoneAuditLog.
     *
     * @param taxZoneAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    TaxZoneAuditLogDTO update(TaxZoneAuditLogDTO taxZoneAuditLogDTO);

    /**
     * Partially updates a taxZoneAuditLog.
     *
     * @param taxZoneAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaxZoneAuditLogDTO> partialUpdate(TaxZoneAuditLogDTO taxZoneAuditLogDTO);

    /**
     * Get all the taxZoneAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaxZoneAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taxZoneAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxZoneAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" taxZoneAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
