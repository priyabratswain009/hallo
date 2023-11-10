package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PosMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PosMasterAuditLog}.
 */
public interface PosMasterAuditLogService {
    /**
     * Save a posMasterAuditLog.
     *
     * @param posMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    PosMasterAuditLogDTO save(PosMasterAuditLogDTO posMasterAuditLogDTO);

    /**
     * Updates a posMasterAuditLog.
     *
     * @param posMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    PosMasterAuditLogDTO update(PosMasterAuditLogDTO posMasterAuditLogDTO);

    /**
     * Partially updates a posMasterAuditLog.
     *
     * @param posMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PosMasterAuditLogDTO> partialUpdate(PosMasterAuditLogDTO posMasterAuditLogDTO);

    /**
     * Get all the posMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PosMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" posMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PosMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" posMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
