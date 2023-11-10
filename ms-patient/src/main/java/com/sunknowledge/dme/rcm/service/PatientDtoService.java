package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientDto}.
 */
public interface PatientDtoService {
    /**
     * Save a patientDto.
     *
     * @param patientDtoDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientDtoDTO> save(PatientDtoDTO patientDtoDTO);

    /**
     * Updates a patientDto.
     *
     * @param patientDtoDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientDtoDTO> update(PatientDtoDTO patientDtoDTO);

    /**
     * Partially updates a patientDto.
     *
     * @param patientDtoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientDtoDTO> partialUpdate(PatientDtoDTO patientDtoDTO);

    /**
     * Get all the patientDtos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientDtoDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientDtos available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientDto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientDtoDTO> findOne(Long id);

    /**
     * Delete the "id" patientDto.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
