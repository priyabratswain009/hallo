package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsurance}.
 */
public interface PatientInsuranceService {
    /**
     * Save a patientInsurance.
     *
     * @param patientInsuranceDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceDTO> save(PatientInsuranceDTO patientInsuranceDTO);

    /**
     * Updates a patientInsurance.
     *
     * @param patientInsuranceDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceDTO> update(PatientInsuranceDTO patientInsuranceDTO);

    /**
     * Partially updates a patientInsurance.
     *
     * @param patientInsuranceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientInsuranceDTO> partialUpdate(PatientInsuranceDTO patientInsuranceDTO);

    /**
     * Get all the patientInsurances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientInsuranceDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientInsurances available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientInsurance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientInsuranceDTO> findOne(Long id);

    /**
     * Delete the "id" patientInsurance.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
