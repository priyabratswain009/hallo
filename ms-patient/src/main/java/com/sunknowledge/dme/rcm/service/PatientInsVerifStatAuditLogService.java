package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog}.
 */
public interface PatientInsVerifStatAuditLogService {
    /**
     * Save a patientInsVerifStatAuditLog.
     *
     * @param patientInsVerifStatAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatAuditLogDTO> save(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO);

    /**
     * Updates a patientInsVerifStatAuditLog.
     *
     * @param patientInsVerifStatAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatAuditLogDTO> update(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO);

    /**
     * Partially updates a patientInsVerifStatAuditLog.
     *
     * @param patientInsVerifStatAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatAuditLogDTO> partialUpdate(PatientInsVerifStatAuditLogDTO patientInsVerifStatAuditLogDTO);

    /**
     * Get all the patientInsVerifStatAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientInsVerifStatAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientInsVerifStatAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientInsVerifStatAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientInsVerifStatAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientInsVerifStatAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
