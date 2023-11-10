package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DoctorMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DoctorMaster}.
 */
public interface DoctorMasterService {
    /**
     * Save a doctorMaster.
     *
     * @param doctorMasterDTO the entity to save.
     * @return the persisted entity.
     */
    DoctorMasterDTO save(DoctorMasterDTO doctorMasterDTO);

    /**
     * Updates a doctorMaster.
     *
     * @param doctorMasterDTO the entity to update.
     * @return the persisted entity.
     */
    DoctorMasterDTO update(DoctorMasterDTO doctorMasterDTO);

    /**
     * Partially updates a doctorMaster.
     *
     * @param doctorMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DoctorMasterDTO> partialUpdate(DoctorMasterDTO doctorMasterDTO);

    /**
     * Get all the doctorMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DoctorMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" doctorMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DoctorMasterDTO> findOne(Long id);

    /**
     * Delete the "id" doctorMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
