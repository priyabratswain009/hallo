package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ChecklistDocumentReferenceMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ChecklistDocumentReferenceMap}.
 */
public interface ChecklistDocumentReferenceMapService {
    /**
     * Save a checklistDocumentReferenceMap.
     *
     * @param checklistDocumentReferenceMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ChecklistDocumentReferenceMapDTO> save(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO);

    /**
     * Updates a checklistDocumentReferenceMap.
     *
     * @param checklistDocumentReferenceMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<ChecklistDocumentReferenceMapDTO> update(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO);

    /**
     * Partially updates a checklistDocumentReferenceMap.
     *
     * @param checklistDocumentReferenceMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ChecklistDocumentReferenceMapDTO> partialUpdate(ChecklistDocumentReferenceMapDTO checklistDocumentReferenceMapDTO);

    /**
     * Get all the checklistDocumentReferenceMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ChecklistDocumentReferenceMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of checklistDocumentReferenceMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" checklistDocumentReferenceMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ChecklistDocumentReferenceMapDTO> findOne(Long id);

    /**
     * Delete the "id" checklistDocumentReferenceMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
