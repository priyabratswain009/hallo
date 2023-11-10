package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDiagnosisAuditLog}.
 */
public interface PatientDiagnosisAuditLogService {
    /**
     * Save a patientDiagnosisAuditLog.
     *
     * @param patientDiagnosisAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisAuditLogDTO> save(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO);

    /**
     * Updates a patientDiagnosisAuditLog.
     *
     * @param patientDiagnosisAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisAuditLogDTO> update(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO);

    /**
     * Partially updates a patientDiagnosisAuditLog.
     *
     * @param patientDiagnosisAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisAuditLogDTO> partialUpdate(PatientDiagnosisAuditLogDTO patientDiagnosisAuditLogDTO);

    /**
     * Get all the patientDiagnosisAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDiagnosisAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDiagnosisAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDiagnosisAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDiagnosisAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientDiagnosisAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
