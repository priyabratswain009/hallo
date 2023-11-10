package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.WorkersCompensationAuditLog}.
 */
public interface WorkersCompensationAuditLogService {
    /**
     * Save a workersCompensationAuditLog.
     *
     * @param workersCompensationAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationAuditLogDTO> save(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO);

    /**
     * Updates a workersCompensationAuditLog.
     *
     * @param workersCompensationAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationAuditLogDTO> update(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO);

    /**
     * Partially updates a workersCompensationAuditLog.
     *
     * @param workersCompensationAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<WorkersCompensationAuditLogDTO> partialUpdate(WorkersCompensationAuditLogDTO workersCompensationAuditLogDTO);

    /**
     * Get all the workersCompensationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<WorkersCompensationAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of workersCompensationAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" workersCompensationAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<WorkersCompensationAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" workersCompensationAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
