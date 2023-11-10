package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.Claim835277ExceptionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.Claim835277Exception}.
 */
public interface Claim835277ExceptionService {
    /**
     * Save a claim835277Exception.
     *
     * @param claim835277ExceptionDTO the entity to save.
     * @return the persisted entity.
     */
    Claim835277ExceptionDTO save(Claim835277ExceptionDTO claim835277ExceptionDTO);

    /**
     * Updates a claim835277Exception.
     *
     * @param claim835277ExceptionDTO the entity to update.
     * @return the persisted entity.
     */
    Claim835277ExceptionDTO update(Claim835277ExceptionDTO claim835277ExceptionDTO);

    /**
     * Partially updates a claim835277Exception.
     *
     * @param claim835277ExceptionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Claim835277ExceptionDTO> partialUpdate(Claim835277ExceptionDTO claim835277ExceptionDTO);

    /**
     * Get all the claim835277Exceptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Claim835277ExceptionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" claim835277Exception.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Claim835277ExceptionDTO> findOne(Long id);

    /**
     * Delete the "id" claim835277Exception.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
