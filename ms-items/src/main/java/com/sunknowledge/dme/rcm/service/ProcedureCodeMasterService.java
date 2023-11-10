package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ProcedureCodeMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster}.
 */
public interface ProcedureCodeMasterService {
    /**
     * Save a procedureCodeMaster.
     *
     * @param procedureCodeMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ProcedureCodeMasterDTO save(ProcedureCodeMasterDTO procedureCodeMasterDTO);

    /**
     * Updates a procedureCodeMaster.
     *
     * @param procedureCodeMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ProcedureCodeMasterDTO update(ProcedureCodeMasterDTO procedureCodeMasterDTO);

    /**
     * Partially updates a procedureCodeMaster.
     *
     * @param procedureCodeMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProcedureCodeMasterDTO> partialUpdate(ProcedureCodeMasterDTO procedureCodeMasterDTO);

    /**
     * Get all the procedureCodeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProcedureCodeMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" procedureCodeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProcedureCodeMasterDTO> findOne(Long id);

    /**
     * Delete the "id" procedureCodeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
