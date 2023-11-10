package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ClaimsReportFileProcessStatusDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ClaimsReportFileProcessStatus}.
 */
public interface ClaimsReportFileProcessStatusService {
    /**
     * Save a claimsReportFileProcessStatus.
     *
     * @param claimsReportFileProcessStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimsReportFileProcessStatusDTO save(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO);

    /**
     * Updates a claimsReportFileProcessStatus.
     *
     * @param claimsReportFileProcessStatusDTO the entity to update.
     * @return the persisted entity.
     */
    ClaimsReportFileProcessStatusDTO update(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO);

    /**
     * Partially updates a claimsReportFileProcessStatus.
     *
     * @param claimsReportFileProcessStatusDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClaimsReportFileProcessStatusDTO> partialUpdate(ClaimsReportFileProcessStatusDTO claimsReportFileProcessStatusDTO);

    /**
     * Get all the claimsReportFileProcessStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimsReportFileProcessStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claimsReportFileProcessStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimsReportFileProcessStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimsReportFileProcessStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
