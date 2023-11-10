package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocumentAuditLog}.
 */
public interface PatientDocumentAuditLogService {
    /**
     * Save a patientDocumentAuditLog.
     *
     * @param patientDocumentAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDocumentAuditLogDTO> save(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO);

    /**
     * Updates a patientDocumentAuditLog.
     *
     * @param patientDocumentAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDocumentAuditLogDTO> update(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO);

    /**
     * Partially updates a patientDocumentAuditLog.
     *
     * @param patientDocumentAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDocumentAuditLogDTO> partialUpdate(PatientDocumentAuditLogDTO patientDocumentAuditLogDTO);

    /**
     * Get all the patientDocumentAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDocumentAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDocumentAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDocumentAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDocumentAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientDocumentAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
