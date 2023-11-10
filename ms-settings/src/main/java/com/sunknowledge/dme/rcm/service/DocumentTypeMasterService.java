package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DocumentTypeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DocumentTypeMaster}.
 */
public interface DocumentTypeMasterService {
    /**
     * Save a documentTypeMaster.
     *
     * @param documentTypeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentTypeMasterDTO save(DocumentTypeMasterDTO documentTypeMasterDTO);

    /**
     * Updates a documentTypeMaster.
     *
     * @param documentTypeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    DocumentTypeMasterDTO update(DocumentTypeMasterDTO documentTypeMasterDTO);

    /**
     * Partially updates a documentTypeMaster.
     *
     * @param documentTypeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DocumentTypeMasterDTO> partialUpdate(DocumentTypeMasterDTO documentTypeMasterDTO);

    /**
     * Get all the documentTypeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DocumentTypeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" documentTypeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentTypeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" documentTypeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
