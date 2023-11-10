package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog}.
 */
public interface PatientMasterAuditLogService {
    /**
     * Save a patientMasterAuditLog.
     *
     * @param patientMasterAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientMasterAuditLogDTO> save(PatientMasterAuditLogDTO patientMasterAuditLogDTO);

    /**
     * Updates a patientMasterAuditLog.
     *
     * @param patientMasterAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientMasterAuditLogDTO> update(PatientMasterAuditLogDTO patientMasterAuditLogDTO);

    /**
     * Partially updates a patientMasterAuditLog.
     *
     * @param patientMasterAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientMasterAuditLogDTO> partialUpdate(PatientMasterAuditLogDTO patientMasterAuditLogDTO);

    /**
     * Get all the patientMasterAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientMasterAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientMasterAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientMasterAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientMasterAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
