package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsuranceAuditLog}.
 */
public interface PatientInsuranceAuditLogService {
    /**
     * Save a patientInsuranceAuditLog.
     *
     * @param patientInsuranceAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceAuditLogDTO> save(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO);

    /**
     * Updates a patientInsuranceAuditLog.
     *
     * @param patientInsuranceAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceAuditLogDTO> update(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO);

    /**
     * Partially updates a patientInsuranceAuditLog.
     *
     * @param patientInsuranceAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceAuditLogDTO> partialUpdate(PatientInsuranceAuditLogDTO patientInsuranceAuditLogDTO);

    /**
     * Get all the patientInsuranceAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientInsuranceAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientInsuranceAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientInsuranceAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientInsuranceAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientInsuranceAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
