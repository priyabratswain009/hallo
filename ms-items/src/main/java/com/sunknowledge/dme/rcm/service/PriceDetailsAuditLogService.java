package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog}.
 */
public interface PriceDetailsAuditLogService {
    /**
     * Save a priceDetailsAuditLog.
     *
     * @param priceDetailsAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PriceDetailsAuditLogDTO save(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO);

    /**
     * Updates a priceDetailsAuditLog.
     *
     * @param priceDetailsAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PriceDetailsAuditLogDTO update(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO);

    /**
     * Partially updates a priceDetailsAuditLog.
     *
     * @param priceDetailsAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PriceDetailsAuditLogDTO> partialUpdate(PriceDetailsAuditLogDTO priceDetailsAuditLogDTO);

    /**
     * Get all the priceDetailsAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PriceDetailsAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" priceDetailsAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PriceDetailsAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" priceDetailsAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
