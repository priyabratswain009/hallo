package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.SalesOrderClassificationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.SalesOrderClassification}.
 */
public interface SalesOrderClassificationService {
    /**
     * Save a salesOrderClassification.
     *
     * @param salesOrderClassificationDTO the entity to save.
     * @return the persisted entity.
     */
    SalesOrderClassificationDTO save(SalesOrderClassificationDTO salesOrderClassificationDTO);

    /**
     * Updates a salesOrderClassification.
     *
     * @param salesOrderClassificationDTO the entity to update.
     * @return the persisted entity.
     */
    SalesOrderClassificationDTO update(SalesOrderClassificationDTO salesOrderClassificationDTO);

    /**
     * Partially updates a salesOrderClassification.
     *
     * @param salesOrderClassificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesOrderClassificationDTO> partialUpdate(SalesOrderClassificationDTO salesOrderClassificationDTO);

    /**
     * Get all the salesOrderClassifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesOrderClassificationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesOrderClassification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesOrderClassificationDTO> findOne(Long id);

    /**
     * Delete the "id" salesOrderClassification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
