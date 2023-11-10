package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap}.
 */
public interface PatientDocumentSoMapService {
    /**
     * Save a patientDocumentSoMap.
     *
     * @param patientDocumentSoMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDocumentSoMapDTO> save(PatientDocumentSoMapDTO patientDocumentSoMapDTO);

    /**
     * Updates a patientDocumentSoMap.
     *
     * @param patientDocumentSoMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDocumentSoMapDTO> update(PatientDocumentSoMapDTO patientDocumentSoMapDTO);

    /**
     * Partially updates a patientDocumentSoMap.
     *
     * @param patientDocumentSoMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDocumentSoMapDTO> partialUpdate(PatientDocumentSoMapDTO patientDocumentSoMapDTO);

    /**
     * Get all the patientDocumentSoMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDocumentSoMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDocumentSoMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDocumentSoMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDocumentSoMapDTO> findOne(Long id);

    /**
     * Delete the "id" patientDocumentSoMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
