package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.CmnDocumentMaster}.
 */
public interface CmnDocumentMasterService {
    /**
     * Save a cmnDocumentMaster.
     *
     * @param cmnDocumentMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<CmnDocumentMasterDTO> save(CmnDocumentMasterDTO cmnDocumentMasterDTO);

    /**
     * Updates a cmnDocumentMaster.
     *
     * @param cmnDocumentMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<CmnDocumentMasterDTO> update(CmnDocumentMasterDTO cmnDocumentMasterDTO);

    /**
     * Partially updates a cmnDocumentMaster.
     *
     * @param cmnDocumentMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<CmnDocumentMasterDTO> partialUpdate(CmnDocumentMasterDTO cmnDocumentMasterDTO);

    /**
     * Get all the cmnDocumentMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<CmnDocumentMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of cmnDocumentMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" cmnDocumentMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<CmnDocumentMasterDTO> findOne(Long id);

    /**
     * Delete the "id" cmnDocumentMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
