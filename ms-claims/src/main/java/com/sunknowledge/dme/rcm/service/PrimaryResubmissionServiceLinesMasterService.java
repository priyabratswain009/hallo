package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PrimaryResubmissionServiceLinesMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster}.
 */
public interface PrimaryResubmissionServiceLinesMasterService {
    /**
     * Save a primaryResubmissionServiceLinesMaster.
     *
     * @param primaryResubmissionServiceLinesMasterDTO the entity to save.
     * @return the persisted entity.
     */
    PrimaryResubmissionServiceLinesMasterDTO save(PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO);

    /**
     * Updates a primaryResubmissionServiceLinesMaster.
     *
     * @param primaryResubmissionServiceLinesMasterDTO the entity to update.
     * @return the persisted entity.
     */
    PrimaryResubmissionServiceLinesMasterDTO update(PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO);

    /**
     * Partially updates a primaryResubmissionServiceLinesMaster.
     *
     * @param primaryResubmissionServiceLinesMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PrimaryResubmissionServiceLinesMasterDTO> partialUpdate(
        PrimaryResubmissionServiceLinesMasterDTO primaryResubmissionServiceLinesMasterDTO
    );

    /**
     * Get all the primaryResubmissionServiceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PrimaryResubmissionServiceLinesMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" primaryResubmissionServiceLinesMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PrimaryResubmissionServiceLinesMasterDTO> findOne(Long id);

    /**
     * Delete the "id" primaryResubmissionServiceLinesMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
