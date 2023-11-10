package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.ServiceLinesMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.ServiceLinesMaster}.
 */
public interface ServiceLinesMasterService {
    /**
     * Save a serviceLinesMaster.
     *
     * @param serviceLinesMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceLinesMasterDTO save(ServiceLinesMasterDTO serviceLinesMasterDTO);

    /**
     * Updates a serviceLinesMaster.
     *
     * @param serviceLinesMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ServiceLinesMasterDTO update(ServiceLinesMasterDTO serviceLinesMasterDTO);

    /**
     * Partially updates a serviceLinesMaster.
     *
     * @param serviceLinesMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServiceLinesMasterDTO> partialUpdate(ServiceLinesMasterDTO serviceLinesMasterDTO);

    /**
     * Get all the serviceLinesMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceLinesMasterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" serviceLinesMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceLinesMasterDTO> findOne(Long id);

    /**
     * Delete the "id" serviceLinesMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
