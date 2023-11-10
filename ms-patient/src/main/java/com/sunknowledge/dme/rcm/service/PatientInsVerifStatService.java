package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PatientInsVerifStat}.
 */
public interface PatientInsVerifStatService {
    /**
     * Save a patientInsVerifStat.
     *
     * @param patientInsVerifStatDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatDTO> save(PatientInsVerifStatDTO patientInsVerifStatDTO);

    /**
     * Updates a patientInsVerifStat.
     *
     * @param patientInsVerifStatDTO the entity to update.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatDTO> update(PatientInsVerifStatDTO patientInsVerifStatDTO);

    /**
     * Partially updates a patientInsVerifStat.
     *
     * @param patientInsVerifStatDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PatientInsVerifStatDTO> partialUpdate(PatientInsVerifStatDTO patientInsVerifStatDTO);

    /**
     * Get all the patientInsVerifStats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<PatientInsVerifStatDTO> findAll(Pageable pageable);

    /**
     * Returns the number of patientInsVerifStats available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" patientInsVerifStat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PatientInsVerifStatDTO> findOne(Long id);

    /**
     * Delete the "id" patientInsVerifStat.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
