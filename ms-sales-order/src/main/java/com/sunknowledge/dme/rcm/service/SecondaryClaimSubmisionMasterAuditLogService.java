package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog}.
 */
public interface SecondaryClaimSubmisionMasterAuditLogService {
    /**
     * Save a secondaryClaimSubmisionMasterAuditLog.
     *
     * @param secondaryClaimSubmisionMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterAuditLogDTO> save(SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO);

    /**
     * Updates a secondaryClaimSubmisionMasterAuditLog.
     *
     * @param secondaryClaimSubmisionMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterAuditLogDTO> update(
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    );

    /**
     * Partially updates a secondaryClaimSubmisionMasterAuditLog.
     *
     * @param secondaryClaimSubmisionMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SecondaryClaimSubmisionMasterAuditLogDTO> partialUpdate(
        SecondaryClaimSubmisionMasterAuditLogDTO secondaryClaimSubmisionMasterAuditLogDTO
    );

    /**
     * Get all the secondaryClaimSubmisionMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SecondaryClaimSubmisionMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of secondaryClaimSubmisionMasterAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" secondaryClaimSubmisionMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SecondaryClaimSubmisionMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" secondaryClaimSubmisionMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
