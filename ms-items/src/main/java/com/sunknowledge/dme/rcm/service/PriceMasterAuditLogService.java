package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog}.
 */
public interface PriceMasterAuditLogService {
    /**
     * Save a priceMasterAuditLog.
     *
     * @param priceMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PriceMasterAuditLogDTO save(PriceMasterAuditLogDTO priceMasterAuditLogDTO);

    /**
     * Updates a priceMasterAuditLog.
     *
     * @param priceMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PriceMasterAuditLogDTO update(PriceMasterAuditLogDTO priceMasterAuditLogDTO);

    /**
     * Partially updates a priceMasterAuditLog.
     *
     * @param priceMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PriceMasterAuditLogDTO> partialUpdate(PriceMasterAuditLogDTO priceMasterAuditLogDTO);

    /**
     * Get all the priceMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PriceMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" priceMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PriceMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" priceMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
