package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog}.
 */
public interface HoldReasonMasterAuditLogService {
    /**
     * Save a holdReasonMasterAuditLog.
     *
     * @param holdReasonMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    HoldReasonMasterAuditLogDTO save(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO);

    /**
     * Updates a holdReasonMasterAuditLog.
     *
     * @param holdReasonMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    HoldReasonMasterAuditLogDTO update(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO);

    /**
     * Partially updates a holdReasonMasterAuditLog.
     *
     * @param holdReasonMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HoldReasonMasterAuditLogDTO> partialUpdate(HoldReasonMasterAuditLogDTO holdReasonMasterAuditLogDTO);

    /**
     * Get all the holdReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HoldReasonMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" holdReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HoldReasonMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" holdReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
