package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.InsuranceDocumentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.InsuranceDocument}.
 */
public interface InsuranceDocumentService {
    /**
     * Save a insuranceDocument.
     *
     * @param insuranceDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceDocumentDTO save(InsuranceDocumentDTO insuranceDocumentDTO);

    /**
     * Updates a insuranceDocument.
     *
     * @param insuranceDocumentDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceDocumentDTO update(InsuranceDocumentDTO insuranceDocumentDTO);

    /**
     * Partially updates a insuranceDocument.
     *
     * @param insuranceDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceDocumentDTO> partialUpdate(InsuranceDocumentDTO insuranceDocumentDTO);

    /**
     * Get all the insuranceDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InsuranceDocumentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" insuranceDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
