package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.BranchOffice}.
 */
public interface BranchOfficeService {
    /**
     * Save a branchOffice.
     *
     * @param branchOfficeDTO the entity to save.
     * @return the persisted entity.
     */
    BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO);

    /**
     * Updates a branchOffice.
     *
     * @param branchOfficeDTO the entity to update.
     * @return the persisted entity.
     */
    BranchOfficeDTO update(BranchOfficeDTO branchOfficeDTO);

    /**
     * Partially updates a branchOffice.
     *
     * @param branchOfficeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BranchOfficeDTO> partialUpdate(BranchOfficeDTO branchOfficeDTO);

    /**
     * Get all the branchOffices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BranchOfficeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" branchOffice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BranchOfficeDTO> findOne(Long id);

    /**
     * Delete the "id" branchOffice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
