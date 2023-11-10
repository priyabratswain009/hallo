package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PayerMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PayerMaster}.
 */
public interface PayerMasterService {
    /**
     * Save a payerMaster.
     *
     * @param payerMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PayerMasterDTO> save(PayerMasterDTO payerMasterDTO);

    /**
     * Updates a payerMaster.
     *
     * @param payerMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PayerMasterDTO> update(PayerMasterDTO payerMasterDTO);

    /**
     * Partially updates a payerMaster.
     *
     * @param payerMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PayerMasterDTO> partialUpdate(PayerMasterDTO payerMasterDTO);

    /**
     * Get all the payerMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PayerMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of payerMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" payerMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PayerMasterDTO> findOne(Long id);

    /**
     * Delete the "id" payerMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
