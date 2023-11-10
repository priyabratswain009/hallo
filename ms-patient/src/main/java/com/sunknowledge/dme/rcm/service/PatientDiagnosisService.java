package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDiagnosis}.
 */
public interface PatientDiagnosisService {
    /**
     * Save a patientDiagnosis.
     *
     * @param patientDiagnosisDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisDTO> save(PatientDiagnosisDTO patientDiagnosisDTO);

    /**
     * Updates a patientDiagnosis.
     *
     * @param patientDiagnosisDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisDTO> update(PatientDiagnosisDTO patientDiagnosisDTO);

    /**
     * Partially updates a patientDiagnosis.
     *
     * @param patientDiagnosisDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDiagnosisDTO> partialUpdate(PatientDiagnosisDTO patientDiagnosisDTO);

    /**
     * Get all the patientDiagnoses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDiagnosisDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDiagnoses available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDiagnosis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDiagnosisDTO> findOne(Long id);

    /**
     * Delete the "id" patientDiagnosis.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
