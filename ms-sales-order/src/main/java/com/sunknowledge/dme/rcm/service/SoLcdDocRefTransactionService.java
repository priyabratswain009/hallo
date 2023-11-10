package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SoLcdDocRefTransactionDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction}.
 */
public interface SoLcdDocRefTransactionService {
    /**
     * Save a soLcdDocRefTransaction.
     *
     * @param soLcdDocRefTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SoLcdDocRefTransactionDTO> save(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO);

    /**
     * Updates a soLcdDocRefTransaction.
     *
     * @param soLcdDocRefTransactionDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SoLcdDocRefTransactionDTO> update(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO);

    /**
     * Partially updates a soLcdDocRefTransaction.
     *
     * @param soLcdDocRefTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SoLcdDocRefTransactionDTO> partialUpdate(SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO);

    /**
     * Get all the soLcdDocRefTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SoLcdDocRefTransactionDTO> findAll(Pageable pageable);

    /**
     * Returns the number of soLcdDocRefTransactions available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" soLcdDocRefTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SoLcdDocRefTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" soLcdDocRefTransaction.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
