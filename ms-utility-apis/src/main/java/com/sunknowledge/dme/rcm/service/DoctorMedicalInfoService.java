package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.DoctorMedicalInfoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.DoctorMedicalInfo}.
 */
public interface DoctorMedicalInfoService {
    /**
     * Save a doctorMedicalInfo.
     *
     * @param doctorMedicalInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DoctorMedicalInfoDTO save(DoctorMedicalInfoDTO doctorMedicalInfoDTO);

    /**
     * Updates a doctorMedicalInfo.
     *
     * @param doctorMedicalInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DoctorMedicalInfoDTO update(DoctorMedicalInfoDTO doctorMedicalInfoDTO);

    /**
     * Partially updates a doctorMedicalInfo.
     *
     * @param doctorMedicalInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DoctorMedicalInfoDTO> partialUpdate(DoctorMedicalInfoDTO doctorMedicalInfoDTO);

    /**
     * Get all the doctorMedicalInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DoctorMedicalInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" doctorMedicalInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DoctorMedicalInfoDTO> findOne(Long id);

    /**
     * Delete the "id" doctorMedicalInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
