package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDoctorMap}.
 */
public interface PatientDoctorMapService {
    /**
     * Save a patientDoctorMap.
     *
     * @param patientDoctorMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapDTO> save(PatientDoctorMapDTO patientDoctorMapDTO);

    /**
     * Updates a patientDoctorMap.
     *
     * @param patientDoctorMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapDTO> update(PatientDoctorMapDTO patientDoctorMapDTO);

    /**
     * Partially updates a patientDoctorMap.
     *
     * @param patientDoctorMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDoctorMapDTO> partialUpdate(PatientDoctorMapDTO patientDoctorMapDTO);

    /**
     * Get all the patientDoctorMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDoctorMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDoctorMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDoctorMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDoctorMapDTO> findOne(Long id);

    /**
     * Delete the "id" patientDoctorMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
