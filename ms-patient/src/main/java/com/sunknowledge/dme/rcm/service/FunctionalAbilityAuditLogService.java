package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog}.
 */
public interface FunctionalAbilityAuditLogService {
    /**
     * Save a functionalAbilityAuditLog.
     *
     * @param functionalAbilityAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityAuditLogDTO> save(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO);

    /**
     * Updates a functionalAbilityAuditLog.
     *
     * @param functionalAbilityAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityAuditLogDTO> update(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO);

    /**
     * Partially updates a functionalAbilityAuditLog.
     *
     * @param functionalAbilityAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<FunctionalAbilityAuditLogDTO> partialUpdate(FunctionalAbilityAuditLogDTO functionalAbilityAuditLogDTO);

    /**
     * Get all the functionalAbilityAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<FunctionalAbilityAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of functionalAbilityAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" functionalAbilityAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<FunctionalAbilityAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" functionalAbilityAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
