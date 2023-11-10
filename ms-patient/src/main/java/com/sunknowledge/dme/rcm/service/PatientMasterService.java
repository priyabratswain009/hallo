package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientMaster}.
 */
public interface PatientMasterService {
    /**
     * Save a patientMaster.
     *
     * @param patientMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientMasterDTO> save(PatientMasterDTO patientMasterDTO);

    /**
     * Updates a patientMaster.
     *
     * @param patientMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientMasterDTO> update(PatientMasterDTO patientMasterDTO);

    /**
     * Partially updates a patientMaster.
     *
     * @param patientMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientMasterDTO> partialUpdate(PatientMasterDTO patientMasterDTO);

    /**
     * Get all the patientMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientMasterDTO> findOne(Long id);

    /**
     * Delete the "id" patientMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
