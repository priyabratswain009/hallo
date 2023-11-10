package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapAuditLogDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDoctorMapAuditLog}.
 */
public interface PatientDoctorMapAuditLogService {
    /**
     * Save a patientDoctorMapAuditLog.
     *
     * @param patientDoctorMapAuditLogDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapAuditLogDTO> save(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO);

    /**
     * Updates a patientDoctorMapAuditLog.
     *
     * @param patientDoctorMapAuditLogDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapAuditLogDTO> update(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO);

    /**
     * Partially updates a patientDoctorMapAuditLog.
     *
     * @param patientDoctorMapAuditLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapAuditLogDTO> partialUpdate(PatientDoctorMapAuditLogDTO patientDoctorMapAuditLogDTO);

    /**
     * Get all the patientDoctorMapAuditLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDoctorMapAuditLogDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDoctorMapAuditLogs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDoctorMapAuditLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDoctorMapAuditLogDTO> findOne(Long id);

    /**
     * Delete the "id" patientDoctorMapAuditLog.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
