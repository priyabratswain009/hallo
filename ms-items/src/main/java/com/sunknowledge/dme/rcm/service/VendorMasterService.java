package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.VendorMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.VendorMaster}.
 */
public interface VendorMasterService {
    /**
     * Save a vendorMaster.
     *
     * @param vendorMasterDTO the entity to save.
     * @return the persisted entity.
     */
    VendorMasterDTO save(VendorMasterDTO vendorMasterDTO);

    /**
     * Updates a vendorMaster.
     *
     * @param vendorMasterDTO the entity to update.
     * @return the persisted entity.
     */
    VendorMasterDTO update(VendorMasterDTO vendorMasterDTO);

    /**
     * Partially updates a vendorMaster.
     *
     * @param vendorMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VendorMasterDTO> partialUpdate(VendorMasterDTO vendorMasterDTO);

    /**
     * Get all the vendorMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendorMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" vendorMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendorMasterDTO> findOne(Long id);

    /**
     * Delete the "id" vendorMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
