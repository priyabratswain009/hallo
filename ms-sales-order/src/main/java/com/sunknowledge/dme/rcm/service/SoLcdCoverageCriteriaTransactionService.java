package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SoLcdCoverageCriteriaTransactionDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction}.
 */
public interface SoLcdCoverageCriteriaTransactionService {
    /**
     * Save a soLcdCoverageCriteriaTransaction.
     *
     * @param soLcdCoverageCriteriaTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<SoLcdCoverageCriteriaTransactionDTO> save(SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO);

    /**
     * Updates a soLcdCoverageCriteriaTransaction.
     *
     * @param soLcdCoverageCriteriaTransactionDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<SoLcdCoverageCriteriaTransactionDTO> update(SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO);

    /**
     * Partially updates a soLcdCoverageCriteriaTransaction.
     *
     * @param soLcdCoverageCriteriaTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SoLcdCoverageCriteriaTransactionDTO> partialUpdate(SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO);

    /**
     * Get all the soLcdCoverageCriteriaTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<SoLcdCoverageCriteriaTransactionDTO> findAll(Pageable pageable);

    /**
     * Returns the number of soLcdCoverageCriteriaTransactions available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" soLcdCoverageCriteriaTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SoLcdCoverageCriteriaTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" soLcdCoverageCriteriaTransaction.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
