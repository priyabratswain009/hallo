package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog}.
 */
public interface PatientClinicalInformationAuditLogService {
    /**
     * Save a patientClinicalInformationAuditLog.
     *
     * @param patientClinicalInformationAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationAuditLogDTO> save(PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO);

    /**
     * Updates a patientClinicalInformationAuditLog.
     *
     * @param patientClinicalInformationAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationAuditLogDTO> update(PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO);

    /**
     * Partially updates a patientClinicalInformationAuditLog.
     *
     * @param patientClinicalInformationAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationAuditLogDTO> partialUpdate(PatientClinicalInformationAuditLogDTO patientClinicalInformationAuditLogDTO);

    /**
     * Get all the patientClinicalInformationAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientClinicalInformationAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientClinicalInformationAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientClinicalInformationAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientClinicalInformationAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientClinicalInformationAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
