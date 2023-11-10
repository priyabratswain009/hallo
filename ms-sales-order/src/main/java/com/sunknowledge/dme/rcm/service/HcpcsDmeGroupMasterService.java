package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.HcpcsDmeGroupMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster}.
 */
public interface HcpcsDmeGroupMasterService {
    /**
     * Save a hcpcsDmeGroupMaster.
     *
     * @param hcpcsDmeGroupMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<HcpcsDmeGroupMasterDTO> save(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO);

    /**
     * Updates a hcpcsDmeGroupMaster.
     *
     * @param hcpcsDmeGroupMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<HcpcsDmeGroupMasterDTO> update(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO);

    /**
     * Partially updates a hcpcsDmeGroupMaster.
     *
     * @param hcpcsDmeGroupMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<HcpcsDmeGroupMasterDTO> partialUpdate(HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO);

    /**
     * Get all the hcpcsDmeGroupMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<HcpcsDmeGroupMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of hcpcsDmeGroupMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" hcpcsDmeGroupMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<HcpcsDmeGroupMasterDTO> findOne(Long id);

    /**
     * Delete the "id" hcpcsDmeGroupMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
