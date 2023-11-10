package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.MemberElligibilityDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.MemberElligibility}.
 */
public interface MemberElligibilityService {
    /**
     * Save a memberElligibility.
     *
     * @param memberElligibilityDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityDTO> save(MemberElligibilityDTO memberElligibilityDTO);

    /**
     * Updates a memberElligibility.
     *
     * @param memberElligibilityDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityDTO> update(MemberElligibilityDTO memberElligibilityDTO);

    /**
     * Partially updates a memberElligibility.
     *
     * @param memberElligibilityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<MemberElligibilityDTO> partialUpdate(MemberElligibilityDTO memberElligibilityDTO);

    /**
     * Get all the memberElligibilities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<MemberElligibilityDTO> findAll(Pageable pageable);

    /**
     * Returns the number of memberElligibilities available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" memberElligibility.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<MemberElligibilityDTO> findOne(Long id);

    /**
     * Delete the "id" memberElligibility.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
