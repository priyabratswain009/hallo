package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.FunctionalityEndpointMappingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping}.
 */
public interface FunctionalityEndpointMappingService {
    /**
     * Save a functionalityEndpointMapping.
     *
     * @param functionalityEndpointMappingDTO the entity to save.
     * @return the persisted entity.
     */
    FunctionalityEndpointMappingDTO save(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO);

    /**
     * Updates a functionalityEndpointMapping.
     *
     * @param functionalityEndpointMappingDTO the entity to update.
     * @return the persisted entity.
     */
    FunctionalityEndpointMappingDTO update(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO);

    /**
     * Partially updates a functionalityEndpointMapping.
     *
     * @param functionalityEndpointMappingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FunctionalityEndpointMappingDTO> partialUpdate(FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO);

    /**
     * Get all the functionalityEndpointMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FunctionalityEndpointMappingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" functionalityEndpointMapping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FunctionalityEndpointMappingDTO> findOne(Long id);

    /**
     * Delete the "id" functionalityEndpointMapping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
