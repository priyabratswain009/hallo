package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DocumentReferenceFileMapDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DocumentReferenceFileMap}.
 */
public interface DocumentReferenceFileMapService {
    /**
     * Save a documentReferenceFileMap.
     *
     * @param documentReferenceFileMapDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DocumentReferenceFileMapDTO> save(DocumentReferenceFileMapDTO documentReferenceFileMapDTO);

    /**
     * Updates a documentReferenceFileMap.
     *
     * @param documentReferenceFileMapDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<DocumentReferenceFileMapDTO> update(DocumentReferenceFileMapDTO documentReferenceFileMapDTO);

    /**
     * Partially updates a documentReferenceFileMap.
     *
     * @param documentReferenceFileMapDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DocumentReferenceFileMapDTO> partialUpdate(DocumentReferenceFileMapDTO documentReferenceFileMapDTO);

    /**
     * Get all the documentReferenceFileMaps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DocumentReferenceFileMapDTO> findAll(Pageable pageable);

    /**
     * Returns the number of documentReferenceFileMaps available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" documentReferenceFileMap.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DocumentReferenceFileMapDTO> findOne(Long id);

    /**
     * Delete the "id" documentReferenceFileMap.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
