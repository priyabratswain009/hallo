package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDocument}.
 */
public interface PatientDocumentService {
    /**
     * Save a patientDocument.
     *
     * @param patientDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDocumentDTO> save(PatientDocumentDTO patientDocumentDTO);

    /**
     * Updates a patientDocument.
     *
     * @param patientDocumentDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDocumentDTO> update(PatientDocumentDTO patientDocumentDTO);

    /**
     * Partially updates a patientDocument.
     *
     * @param patientDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDocumentDTO> partialUpdate(PatientDocumentDTO patientDocumentDTO);

    /**
     * Get all the patientDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDocumentDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDocuments available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" patientDocument.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
