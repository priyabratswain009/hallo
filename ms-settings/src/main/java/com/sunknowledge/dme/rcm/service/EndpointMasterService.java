package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.EndpointMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.EndpointMaster}.
 */
public interface EndpointMasterService {
    /**
     * Save a endpointMaster.
     *
     * @param endpointMasterDTO the entity to save.
     * @return the persisted entity.
     */
    EndpointMasterDTO save(EndpointMasterDTO endpointMasterDTO);

    /**
     * Updates a endpointMaster.
     *
     * @param endpointMasterDTO the entity to update.
     * @return the persisted entity.
     */
    EndpointMasterDTO update(EndpointMasterDTO endpointMasterDTO);

    /**
     * Partially updates a endpointMaster.
     *
     * @param endpointMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EndpointMasterDTO> partialUpdate(EndpointMasterDTO endpointMasterDTO);

    /**
     * Get all the endpointMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EndpointMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" endpointMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EndpointMasterDTO> findOne(Long id);

    /**
     * Delete the "id" endpointMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
