package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityMasterDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.MemberElligibilityMaster}.
 */
public interface MemberElligibilityMasterService {
    /**
     * Save a memberElligibilityMaster.
     *
     * @param memberElligibilityMasterDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityMasterDTO> save(MemberElligibilityMasterDTO memberElligibilityMasterDTO);

    /**
     * Updates a memberElligibilityMaster.
     *
     * @param memberElligibilityMasterDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityMasterDTO> update(MemberElligibilityMasterDTO memberElligibilityMasterDTO);

    /**
     * Partially updates a memberElligibilityMaster.
     *
     * @param memberElligibilityMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityMasterDTO> partialUpdate(MemberElligibilityMasterDTO memberElligibilityMasterDTO);

    /**
     * Get all the memberElligibilityMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<MemberElligibilityMasterDTO> findAll(Pageable pageable);

    /**
     * Returns the number of memberElligibilityMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" memberElligibilityMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<MemberElligibilityMasterDTO> findOne(Long id);

    /**
     * Delete the "id" memberElligibilityMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
