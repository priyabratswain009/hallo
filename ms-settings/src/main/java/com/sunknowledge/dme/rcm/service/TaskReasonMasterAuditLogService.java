package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterAuditLogDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog}.
 */
public interface TaskReasonMasterAuditLogService {
    /**
     * Save a taskReasonMasterAuditLog.
     *
     * @param taskReasonMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    TaskReasonMasterAuditLogDTO save(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO);

    /**
     * Updates a taskReasonMasterAuditLog.
     *
     * @param taskReasonMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    TaskReasonMasterAuditLogDTO update(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO);

    /**
     * Partially updates a taskReasonMasterAuditLog.
     *
     * @param taskReasonMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskReasonMasterAuditLogDTO> partialUpdate(TaskReasonMasterAuditLogDTO taskReasonMasterAuditLogDTO);

    /**
     * Get all the taskReasonMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaskReasonMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskReasonMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" taskReasonMasterAuditLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
