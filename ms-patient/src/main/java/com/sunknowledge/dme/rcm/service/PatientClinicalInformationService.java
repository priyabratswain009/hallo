package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientClinicalInformation}.
 */
public interface PatientClinicalInformationService {
    /**
     * Save a patientClinicalInformation.
     *
     * @param patientClinicalInformationDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationDTO> save(PatientClinicalInformationDTO patientClinicalInformationDTO);

    /**
     * Updates a patientClinicalInformation.
     *
     * @param patientClinicalInformationDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationDTO> update(PatientClinicalInformationDTO patientClinicalInformationDTO);

    /**
     * Partially updates a patientClinicalInformation.
     *
     * @param patientClinicalInformationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientClinicalInformationDTO> partialUpdate(PatientClinicalInformationDTO patientClinicalInformationDTO);

    /**
     * Get all the patientClinicalInformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientClinicalInformationDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientClinicalInformations available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientClinicalInformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientClinicalInformationDTO> findOne(Long id);

    /**
     * Delete the "id" patientClinicalInformation.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
